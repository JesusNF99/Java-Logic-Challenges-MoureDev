import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class EJ22 {

    /*
     * EJERCICIO:
     * Explora el concepto de funciones de orden superior en tu lenguaje
     * creando ejemplos simples (a tu elección) que muestren su funcionamiento.
     *
     * DIFICULTAD EXTRA (opcional):
     * Dada una lista de estudiantes (con sus nombres, fecha de nacimiento y
     * lista de calificaciones), utiliza funciones de orden superior para
     * realizar las siguientes operaciones de procesamiento y análisis:
     * - Promedio calificaciones: Obtiene una lista de estudiantes por nombre
     *   y promedio de sus calificaciones.
     * - Mejores estudiantes: Obtiene una lista con el nombre de los estudiantes
     *   que tienen calificaciones con un 9 o más de promedio.
     * - Nacimiento: Obtiene una lista de estudiantes ordenada desde el más joven.
     * - Mayor calificación: Obtiene la calificación más alta de entre todas las
     *   de los alumnos.
     * - Una calificación debe estar comprendida entre 0 y 10 (admite decimales).
     */

    static void main(String[] args) {

        // EJERCICIO
        // 1. Función que recibe otra función como parámetro (executeAction).
        // Se considera de orden superior porque su comportamiento no es fijo;
        // depende de la "receta" (lambda) que le inyectamos desde fuera.
        executeAction(() -> System.out.println("Estoy ejecutando la acción"));

        // 2. Función que retorna otra función (createMultiplier).
        // Es de orden superior porque fabrica "comportamientos" a medida.
        // Aquí creamos una función que "sabe" que debe multiplicar por 3.
        Function<Integer, Integer> triple = createMultiplier(3);
        System.out.println(triple.apply(5)); // Aplica la receta: 5 * 3 = 15

        // EJERCICIO EXTRA
        Student student1 = new Student("Prometheus", LocalDate.of(1998, 11, 30), List.of(5.5, 4.0, 3.5));
        Student student2 = new Student("Faye", LocalDate.of(1999, 12, 31), List.of(7.5, 8.0, 9.5));
        Student student3 = new Student("Hades", LocalDate.of(1999, 1, 12), List.of(6.5, 7.0, 8.5));

        List<Student> students = List.of(student1, student2, student3);

        // Ejecución de los análisis usando Streams
        ListaEstudiantes(students);
        ListaEstudiantesFiltrada(students);
        ListaEstudiantesOrdenada(students);
        MejoresNotas(students);
    }

    // EJERCICIO: Función que recibe función
    public static void executeAction(Runnable action) {
        System.out.println("Antes de la accion");
        action.run(); // Ejecutamos el comportamiento recibido
        System.out.println("Después de la acción");
    }

    // EJERCICIO: Función que retorna función
    public static Function<Integer, Integer> createMultiplier(int factor) {
        // Retornamos una lambda que encapsula el 'factor' para usarlo después
        return (number) -> number * factor;
    }

    // EJERCICIO EXTRA
    public static class Student {
        String nombre;
        LocalDate fechaNacimiento;
        List<Double> calificaciones;

        public Student(String nombre, LocalDate fechaNacimiento, List<Double> calificaciones) {
            this.nombre = nombre;
            this.fechaNacimiento = fechaNacimiento;
            if (!calificaciones.stream().allMatch(c -> c < 0 || c > 10)) {
                this.calificaciones = calificaciones;
            } else throw new IllegalArgumentException("Las calificaciones deben estar entre 0 y 10");

        }

        /**
         * Calcula el promedio usando un pipeline de stream.
         * .mapToDouble convierte los objetos Double en primitivos para operar.
         * .average() es una operación final que busca la media.
         */
        public double getPromedio() {
            return calificaciones.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        }
    }

    public static void ListaEstudiantes(List<Student> students) {
        System.out.println("--- LISTA GENERAL ---");
        students.stream()
                // .map(s -> ...): TRANSFORMA.
                // Recibe un objeto Student y lo convierte en un String con su promedio.
                // Es como meter una naranja y sacar zumo; la cantidad es la misma, la forma cambia.
                .map(s -> s.nombre + " - " + s.getPromedio())
                .forEach(System.out::println);
    }

    public static void ListaEstudiantesFiltrada(List<Student> students) {
        System.out.println("--- FILTRADOS (>8) ---");
        students.stream()
                // .filter(s -> ...): DESCARTA.
                // Actúa como un portero de discoteca: si la condición es true, pasas a la siguiente fase.
                // Si la media es menor o igual a 8, el estudiante es eliminado de la cinta transportadora.
                .filter(s -> s.getPromedio() > 8)
                .map(s -> s.nombre + " - " + s.getPromedio())
                .forEach(System.out::println);
    }

    public static void ListaEstudiantesOrdenada(List<Student> students) {
        System.out.println("--- ORDENADOS POR NACIMIENTO ---");
        students.stream()
                // .sorted(Comparator.comparing(...)): REORDENA.
                // Detiene el flujo, mira las fechas de nacimiento y organiza a los alumnos.
                // Es de orden superior porque le pasas el criterio (la fecha) para comparar.
                .sorted(Comparator.comparing(s -> s.fechaNacimiento))
                .map(s -> s.nombre + " - " + s.fechaNacimiento)
                .forEach(System.out::println);
    }

    public static void MejoresNotas(List<Student> students) {
        System.out.println("--- NOTA MÁXIMA GLOBAL ---");
        students.stream()
                // .flatMap(s -> ...): APLANA.
                // "Abre los sobres": cada estudiante tiene una lista de notas (sobres).
                // FlatMap saca todas las notas individuales y las tira en una sola mesa gigante.
                // Pasamos de tener "Estudiantes con listas" a un chorro de "Double" sueltos.
                .flatMap(s -> s.calificaciones.stream())
                // .max(Double::compare): REDUCE.
                // Recorre tod el montón de notas aplanadas y se queda con la más grande.
                // Double::compare es la referencia a métod para saber cómo comparar dos números.
                .max(Double::compare)
                // .ifPresent(...): SEGURIDAD.
                // Como la lista podría estar vacía y no haber nota máxima, max devuelve un 'Optional'.
                // ifPresent dice: "Si de verdad encontraste un valor ahí dentro, entonces imprímelo".
                .ifPresent(max -> System.out.println("Nota máxima global detectada: " + max));
    }
}
