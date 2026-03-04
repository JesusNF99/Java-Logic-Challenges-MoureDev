import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class EJ03 {

    static HashMap<String, String> agendaContactos = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        /*
         * EJERCICIO:
         * - Muestra ejemplos de creación de todas las estructuras soportadas por defecto en tu lenguaje.
         * - Utiliza operaciones de inserción, borrado, actualización y ordenación.
         *
         * DIFICULTAD EXTRA (opcional):
         * Crea una agenda de contactos por terminal.
         * - Debes implementar funcionalidades de búsqueda, inserción, actualización y eliminación de contactos.
         * - Cada contacto debe tener un nombre y un número de teléfono.
         * - El programa solicita en primer lugar cuál es la operación que se quiere realizar, y a continuación
         *   los datos necesarios para llevarla a cabo.
         * - El programa no puede dejar introducir números de teléfono no numéricos y con más de 11 dígitos.
         *   (o el número de dígitos que quieras)
         * - También se debe proponer una operación de finalización del programa.
         */
        //Estructura 1 - Arrays
        int[] arNumeros = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] arNombres = {"Jack", "John", "Faye"};


        //Imprimiendo arrays
        for (int numero : arNumeros) {
            System.out.println(numero);
        }
        for (String nombre : arNombres) {
            System.out.println(nombre);
        }

        //Estructura 2 - Listas
        ArrayList<String> arlNombres = new ArrayList<>();
        var arlNumeros = new ArrayList<Integer>();

        //Añadir elementos
        arlNombres.add("Jack");
        arlNombres.add("John");
        arlNombres.add("Faye");
        arlNumeros.add(1);
        arlNumeros.add(2);
        arlNumeros.add(3);
        //Imprimiendo Listas
        System.out.println(arlNombres.getFirst());
        System.out.println(arlNombres.get(1));
        System.out.println(arlNombres.getLast());
        System.out.println(arlNumeros.getFirst());
        System.out.println(arlNumeros.get(1));
        System.out.println(arlNumeros.getLast());
        //Modificar elementos
        arlNombres.remove(2);
        arlNombres.add("Prometheus");
        arlNumeros.set(2, 4);
        if (arlNombres.contains("Prometheus")) {
            System.out.println("Eres un: " + arlNombres.get(2));
        }
        System.out.println(arlNombres);


        //Estructura 3 - Sets
        HashSet<String> hsNombres = new HashSet<>();
        var hsNumeros = new HashSet<Integer>();
        //Añadir elementos
        hsNombres.add("Jack");
        hsNombres.add("John");
        hsNombres.add("Faye");
        hsNombres.add("Prometheus");
        hsNumeros.add(1);
        hsNumeros.add(2);
        hsNumeros.add(3);
        //Imprimiendo Sets
        System.out.println(hsNombres);
        System.out.println(hsNumeros);
        //Modificar elementos
        hsNombres.remove("Prometheus");
        hsNumeros.add(4);
        System.out.println(hsNumeros.size());


        //Estructura 4 - Mapas
        HashMap<String, String> hmNombres = new HashMap<>();
        var hmNumeros = new HashMap<Integer, Integer>();
        //Añadir elementos
        hmNombres.put("Jack", "Human");
        hmNombres.put("Prometheus", "God");
        hmNumeros.put(1, 2);
        hmNumeros.put(3, 4);
        //Imprimiendo Mapas
        System.out.println(hmNombres);
        System.out.println(hmNumeros);
        System.out.println(hmNombres.get("Prometheus"));
        //Verificar elementos
        System.out.println(hmNombres.containsKey("Jack"));

        // EJERCICIO EXTRA
        int opcion;
        
        do {
            System.out.println("### AGENDA ###");
            System.out.println("### Menú Principal ###");
            System.out.println("1. Añadir contacto");
            System.out.println("2. Buscar contacto");
            System.out.println("3. Actualizar contacto");
            System.out.println("4. Eliminar contacto");
            System.out.println("5. Salir");
            System.out.println("Selecciona una opción");
            opcion = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (opcion) {
                case 1:
                    //Añadir contacto
                    anadir();
                    break;
                case 2:
                    //Buscar contacto
                    buscar();
                    break;
                case 3:
                    //Actualizar contacto
                    actualizar();
                    break;
                case 4:
                    //Eliminar contacto
                    eliminar();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;

            }
        } while (opcion != 5);
        
        System.out.println("< ==== Salir ==== >");
    }

    public static void anadir() {
        System.out.println("### Añadir contacto ###");
        System.out.println("Introduce el nombre del contacto");
        String nombre = sc.nextLine();
        System.out.println("Introduce el número de teléfono del contacto");
        String telefono = sc.nextLine();
        if (telefono == null || telefono.length() != 9)
            System.out.println("Número de teléfono no válido");
        else {
            agendaContactos.put(nombre, telefono);
            System.out.println("Contacto añadido");
        }
    }

    public static void buscar() {
        System.out.println("### Buscar contacto ###");
        System.out.println("Introduce el nombre del contacto");
        String nombre = sc.nextLine();
        if (agendaContactos.containsKey(nombre)) {
            System.out.println("El número de teléfono del contacto es: " + agendaContactos.get(nombre));
        } else {
            System.out.println("Contacto no encontrado");
        }
    }

    public static void actualizar() {
        System.out.println("### Actualizar contacto ###");
        System.out.println("Introduce el nombre del contacto");
        String nombre = sc.nextLine();
        if (agendaContactos.containsKey(nombre)) {
            System.out.println("Introduce el nuevo número de teléfono del contacto");
            String telefono = sc.nextLine();
            if (telefono == null || telefono.length() != 9)
                System.out.println("Número de teléfono no válido");
            else {
                agendaContactos.put(nombre, telefono);
                System.out.println("Contacto actualizado");
            }
        } else {
            System.out.println("Contacto no encontrado");
        }
    }

    public static void eliminar() {
        System.out.println("### Eliminar contacto ###");
        System.out.println("Introduce el nombre del contacto");
        String nombre = sc.nextLine();
        if (agendaContactos.containsKey(nombre)) {
            agendaContactos.remove(nombre);
            System.out.println("Contacto eliminado");
        } else {
            System.out.println("Contacto no encontrado");
        }
    }
}