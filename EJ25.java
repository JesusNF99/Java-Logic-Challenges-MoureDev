import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.*;

public class EJ25 {
    /*
     * EJERCICIO:
     * Explora el concepto de "logging" en tu lenguaje. Configúralo y muestra
     * un ejemplo con cada nivel de "severidad" disponible.
     *
     * DIFICULTAD EXTRA (opcional):
     * Crea un programa ficticio de gestión de tareas que permita añadir, eliminar
     * y listar dichas tareas.
     * - Añadir: recibe nombre y descripción.
     * - Eliminar: por nombre de la tarea.
     * Implementa diferentes mensajes de log que muestren información según la
     * tarea ejecutada (a tu elección).
     * Utiliza el log para visualizar el tiempo de ejecución de cada tarea.
     */




    // Definimos el logger oficial de Java (JUL)
    private static final Logger logger = Logger.getLogger(EJ25.class.getName());
    // El HashMap permite acceso rápido por nombre (clave única)
    public static HashMap<String, String> tasksList = new HashMap<>();

    public static void main(String[] args) {
        // Configuramos el nivel del logger. INFO es el predeterminado.
        logger.info("Sistema arrancado correctamente.");
        menuPrincipal();
    }

    public static void menuPrincipal() {
        Scanner sc = new Scanner(System.in);
        String navegacion;

        do {
            System.out.println("\n--- Menú de Tareas ---");
            System.out.println("(1) Añadir (2) Eliminar (3) Listar (4) Salir");

            // DUDA SOLUCIONADA: La lectura debe estar dentro del bucle para actualizar la opción
            navegacion = sc.nextLine();

            switch (navegacion) {
                case "1": addTask(); break;
                case "2": deleteTask(); break;
                case "3": listTasks(); break;
                case "4": logger.info("Sesión finalizada por el usuario."); break;
                default: logger.warning("Opción no válida introducida.");
            }
        } while (!navegacion.equals("4"));
    }

    public static void addTask() {
        long startTime = System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Descripción: ");
        String desc = sc.nextLine();

        // DUDA SOLUCIONADA: .containsKey() ya devuelve un booleano, no hay que compararlo con el nombre
        if (tasksList.containsKey(nombre)) {
            logger.warning("Intento de duplicado: " + nombre);
            System.out.println("La tarea ya existe.");
        } else {
            tasksList.put(nombre, desc);
            logger.info("Tarea guardada: " + nombre);
        }

        long finishTime = System.currentTimeMillis();
        // Dividimos por 1000 para obtener segundos
        logger.info("Operación completada en: " + (finishTime - startTime) / 1000.0 + "s");
    }

    public static void deleteTask() {
        long startTime = System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);
        System.out.print("Nombre a eliminar: ");
        String nombre = sc.nextLine();

        if (tasksList.containsKey(nombre)) {
            tasksList.remove(nombre);
            logger.info("Tarea eliminada: " + nombre);
        } else {
            logger.warning("Fallo al borrar: " + nombre + " no existe.");
        }

        long finishTime = System.currentTimeMillis();
        logger.info("Operación completada en: " + (finishTime - startTime) / 1000.0 + "s");
    }

    public static void listTasks() {
        long startTime = System.currentTimeMillis();

        if (tasksList.isEmpty()) {
            System.out.println("No hay tareas.");
        } else {
            // DUDA SOLUCIONADA: Imprimir el mapa usando forEach (más moderno)
            tasksList.forEach((k, v) -> System.out.println("- " + k + ": " + v));
        }

        long finishTime = System.currentTimeMillis();
        logger.info("Listado generado en: " + (finishTime - startTime) / 1000.0 + "s");
    }
}
