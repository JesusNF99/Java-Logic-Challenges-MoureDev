import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class EJ19 {
    public static void main(String[] args) {
        /*
         * EJERCICIO:
         * Empleando tu lenguaje, explora la definición del tipo de dato
         * que sirva para definir enumeraciones (Enum).
         * Crea un Enum que represente los días de la semana del lunes
         * al domingo, en ese orden. Con ese enumerado, crea una operación
         * que muestre el nombre del día de la semana dependiendo del número entero
         * utilizado (del 1 al 7).
         *
         * DIFICULTAD EXTRA (opcional):
         * Crea un pequeño sistema de gestión del estado de pedidos.
         * Implementa una clase que defina un pedido con las siguientes características:
         * - El pedido tiene un identificador y un estado.
         * - El estado es un Enum con estos valores: PENDIENTE, ENVIADO, ENTREGADO y CANCELADO.
         * - Implementa las funciones que sirvan para modificar el estado:
         *   - Pedido enviado
         *   - Pedido cancelado
         *   - Pedido entregado
         *   (Establece una lógica, por ejemplo, no se puede entregar si no se ha enviado, etc...)
         * - Implementa una función para mostrar un texto descriptivo según el estado actual.
         * - Crea diferentes pedidos y muestra cómo se interactúa con ellos.
         */

        // Days.values() nos devuelve un array con todos los elementos del Enum en orden.
        System.out.println(Days.values()[0]);
        System.out.println(Days.values()[1]);
        System.out.println(Days.values()[2]);
        System.out.println(Days.values()[3]);
        System.out.println(Days.values()[4]);
        System.out.println(Days.values()[5]);
        System.out.println(Days.values()[6]);

        // Lanzamos el sistema de gestión de pedidos
        MenuPrincipal();
    }

    // EJERCICIO: Definición de un Enum simple.
    public enum Days {
        LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO;
    }

    // DIFICULTAD EXTRA
    // Definimos los estados posibles como un tipo de dato cerrado y seguro.
    public enum estadoPedido {
        PENDIENTE, ENVIADO, ENTREGADO, CANCELADO;
    }

    /**
     * Clase estática interna. 'static' aquí significa que la clase no depende
     * de una instancia de EJ19, pero cada 'new Paquete' tendrá su propio ID y estado.
     */
    public static class Paquete {
        estadoPedido estado;
        int ID;

        public Paquete(estadoPedido estado, int ID){
            this.estado = estado;
            this.ID = ID;
        }

        /**
         * Sobrescribimos toString() para que al imprimir la lista o el objeto,
         * veamos los datos del paquete y no la dirección de memoria.
         */
        @Override
        public String toString() {
            return "ID: " + ID + " Estado: " + estado;
        }
    }

    // Lista global estática para almacenar los pedidos creados durante la ejecución.
    public static ArrayList<Paquete> listadoPaquetes = new ArrayList<>();

    public static void showMenu() {
        System.out.println("--------------------------------------" + "\n" + "MENÚ DEL SISTEMA DE PEDIDOS" + "\n" + "--------------------------------------");
        System.out.println("Pulse (1) para crear un pedido" + "\n" + "Pulse (2) para cancelar un pedido" + "\n" + "Pulse (3) para entregar un pedido" + "\n" + "Pulse (4) para enviar el pedido" + "\n" + "Pulse (5) para salir");
    }

    public static void MenuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        String navegacion;

        do {
            showMenu();
            navegacion = scanner.nextLine();

            // Usamos switch para manejar las opciones del menú de forma limpia.
            switch (navegacion) {
                case "1":
                    anadirPedido();
                    break;
                case "2":
                    // Validación previa: comprobamos si hay algo que cancelar.
                    if (listadoPaquetes.isEmpty()) {
                        System.out.println("No hay pedidos para cancelar");
                        break;
                    }
                    cancelarPedido();
                    break;
                case "3":
                    if (listadoPaquetes.isEmpty()) {
                        System.out.println("No hay pedidos para entregar");
                        break;
                    }
                    entregarPedido();
                    break;
                case "4":
                    if (listadoPaquetes.isEmpty()) {
                        System.out.println("No hay pedidos para enviar");
                        break;
                    }
                    enviarPedido();
                    break;
                case "5":
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.err.println("Selección no válida");
                    break;
            }
        } while (!Objects.equals(navegacion, "5"));
    }

    public static void anadirPedido() {
        // Generamos un ID aleatorio entre 0 y 1000.
        int ID = (int) (Math.random() * 1000);
        estadoPedido estado = estadoPedido.PENDIENTE; // T0d0 pedido empieza Pendiente.

        listadoPaquetes.add(new Paquete(estado, ID));

        System.out.println("Pedido creado con éxito " + "ID: " + ID + " Estado del pedido " + estado);
    }

    public static void cancelarPedido() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Qué pedido desea cancelar?" + "\n" + listadoPaquetes);
        int buscarID = scanner.nextInt();
        scanner.nextLine(); // Limpiamos el buffer tras leer un entero.

        for (Paquete p : listadoPaquetes) {
            if (p.ID == buscarID) {
                p.estado = estadoPedido.CANCELADO;
                System.out.println("Pedido cancelado con éxito ");
            }
        }
    }

    public static void entregarPedido() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Qué pedido desea entregar?" + "\n" + listadoPaquetes);
        int buscarID = scanner.nextInt();
        scanner.nextLine();

        for (Paquete p : listadoPaquetes) {
            if (p.ID == buscarID) {
                // Implementación de la lógica de negocio según el estado actual.
                switch (p.estado) {
                    case PENDIENTE:
                        System.out.println("No se puede entregar un pedido pendiente (debe enviarse primero)");
                        break;
                    case CANCELADO:
                        System.out.println("No se puede entregar un pedido que ha sido cancelado");
                        break;
                    case ENTREGADO:
                        System.out.println("El pedido ya ha sido entregado previamente");
                        break;
                    case ENVIADO:
                        p.estado = estadoPedido.ENTREGADO;
                        System.out.println("Pedido entregado con éxito");
                        break;
                }
            }
        }
    }

    public static void enviarPedido() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Qué pedido desea enviar?" + "\n" + listadoPaquetes);
        int buscarID = scanner.nextInt();
        scanner.nextLine();

        for (Paquete p : listadoPaquetes) {
            if (p.ID == buscarID) {
                switch (p.estado) {
                    case PENDIENTE:
                        p.estado = estadoPedido.ENVIADO;
                        System.out.println("Pedido enviado con éxito");
                        break;
                    case CANCELADO:
                        System.out.println("No se puede enviar un pedido cancelado");
                        break;
                    case ENTREGADO:
                        System.out.println("El pedido ya ha sido entregado, no se puede enviar");
                        break;
                    case ENVIADO:
                        System.out.println("El pedido ya ha sido enviado");
                        break;
                }
            }
        }
    }
}
