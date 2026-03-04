import com.sun.jdi.Method;

import java.util.*;

public class EJ07 {

    public static void main(String[] args) {

        /*
         * EJERCICIO:
         * Implementa los mecanismos de introducción y recuperación de elementos propios de las
         * pilas (stacks - LIFO) y las colas (queue - FIFO) utilizando una estructura de array
         * o lista (dependiendo de las posibilidades de tu lenguaje).
         *
         * DIFICULTAD EXTRA (opcional):
         * - Utilizando la implementación de pila y cadenas de texto, simula el mecanismo adelante/atrás
         *   de un navegador web. Crea un programa en el que puedas navegar a una página o indicarle
         *   que te quieres desplazar adelante o atrás, mostrando en cada caso el nombre de la web.
         *   Las palabras "adelante", "atrás" desencadenan esta acción, el resto se interpreta como
         *   el nombre de una nueva web.
         * - Utilizando la implementación de cola y cadenas de texto, simula el mecanismo de una
         *   impresora compartida que recibe documentos y los imprime cuando así se le indica.
         *   La palabra "imprimir" imprime un elemento de la cola, el resto de palabras se
         *   interpretan como nombres de documentos.
         */

        Stack<String> Papalaya = new Stack<String>();
        Papalaya.push("Maricón");
        System.out.println(Papalaya.peek());
        Papalaya.push("Payaso");
        System.out.println(Papalaya.peek());
        Papalaya.pop();
        System.out.println(Papalaya.peek());
        int index = Papalaya.search("Maricón");
        System.out.println(index);
        int size = Papalaya.size();
        System.out.println(size);
        Papalaya.clear();

        Queue Miguela = new LinkedList();
        Queue Alfonso = new PriorityQueue();

        Miguela.offer("Pajero");
        Miguela.add("Masón");
        System.out.println(Miguela.peek());
        System.out.println(Miguela.element());
        Miguela.remove();


        //TAREA EXTRA

    }

    public static void menuPrincipal(Scanner scanner) {
        Queue<String> impresora = new LinkedList<>();
        Stack<Integer> pagina = new Stack<>();


        System.out.println("____________________________________________________");
        System.out.println("\nMENÚ PRINCIPAL");
        System.out.println("Escriba (1) - NAVEGADOR WEB\nEscriba (2) - IMPRESORA\nEscribe (3) - FINALIZAR PROGRAMA");
        String navegacion = scanner.nextLine();

        switch (navegacion) {
            case "1": {
                // EJERCICIO DIFICULTAD EXTRA NAVEGADOR WEB
                navegadorWeb(pagina,scanner);
            }
            case "2": {
                // EJERCICIO DIFICULTAD EXTRA IMPRESORA
                dpImpre(impresora, scanner);
            }
            case "3": {
                finProceso();
            }
            default:
                System.err.println("Selección no valida");
                menuPrincipal(scanner);
        }
    }

    public static void navegadorWeb(Stack<Integer> pagina, Scanner scanner) {
        String navegacion = "";
        System.out.println("____________________________________________________");
        if (pagina.isEmpty()) {
            pagina.push(1);
            System.out.println("Estás en el Home página " + pagina.peek());
            System.out.println("Escribe (ADELANTAR)" + "\nEscribe (ATRAS)" + "\nEscribe (CERRAR para ir al menú principal)");
            System.out.println("____________________________________________________");
        }
        navegacion = scanner.nextLine();
        navegadorWeb(adelanteAtras(pagina, navegacion.toLowerCase(),scanner),scanner);

    }

    public static Stack<Integer> adelanteAtras(Stack<Integer> pagina, String navegacion, Scanner scanner) {
        switch (navegacion) {
            case "adelantar": {
                pagina.push(pagina.peek() + 1);
                System.out.println("Estás en lo página " + pagina.peek());
                return pagina;
            }
            case "atras": {
                pagina.pop();
                if (pagina.isEmpty()) {
                    System.out.println("Redireccionado al Home");
                } else {
                    System.out.println("Estás en lo página " + pagina.peek());
                }
                return pagina;
            }
            case "cerrar": {
                System.out.println("Navegador cerrado");
                pagina.clear();
                menuPrincipal(scanner);
                break;
            }
            default:
                System.err.println("La opción no es valida");
                return pagina;
        }
        return pagina;
    }

    // EJERCICIO DE IMPRESORA
    // Se crea el menú para el programa de impresora

    public static void dpImpre(Queue<String> impresora, Scanner scanner) {
        String opcion = "";
        System.out.println("____________________________________________________");

        if (impresora.isEmpty()) {
            System.out.println("\nEscribe (1) - ABRIR MENÚ DE LA IMPRESORA");
            System.out.println("Escribe (2) - MENÚ PRINCIPAL");
            System.out.println("Escribe (3) - FINALIZAR PROGRAMA");
            opcion = scanner.nextLine();
            switch (opcion) {
                case "1": {
                    menuImpresora(impresora, scanner);
                }
                case "2": {
                    menuPrincipal(scanner);

                }
                case "3": {
                    finProceso();

                }
                default:
                    System.err.println("La opción no es valida");
                    dpImpre(impresora, scanner);
            }
        }
    }

    public static void menuImpresora(Queue<String> impresora, Scanner scanner) {
        String opcion = "";
        System.out.println("\nLa impresora no tiene archivos para imprimir");
        System.out.println("Escribe (1) - AGREGA UN NUEVO ARCHIVO");
        System.out.println("Escribe (2) - IMPRIMIR ARCHIVO");
        System.out.println("Escribe (3) - IMPRIMIR TODO");
        System.out.println("Escribe (4) - REMOVER ARCHIVO");
        System.out.println("Escribe (5) - REMOVER TODO");
        System.out.println("\nEscribe (6) - MENÚ PRINCIPAL");
        System.out.println("Escribe (7) - FINALIZAR PROGRAMA");

        opcion = scanner.nextLine();
        switch (opcion) {
            case "1": {
                System.out.println("Escribe el nombre del archivo");
                impresora.add(scanner.nextLine());
                System.out.println(impresora);
                menuImpresora(impresora, scanner);
            }
            case "2": {
                if (!impresora.isEmpty()) {
                    System.out.println("El archivo: (" + impresora.peek() + ") fue impreso con éxito");
                    impresora.remove();
                    System.out.println("Pendientes: " + impresora);
                }
                menuImpresora(impresora, scanner);
            }
            case "3": {
                if (!impresora.isEmpty()) {
                    System.out.println("Los archivos: (" + impresora + ") fueron impresos con éxito");
                    impresora.clear();
                }
                menuImpresora(impresora, scanner);
            }

            case "4": {
                if (!impresora.isEmpty()) {
                    System.err.println("El archivo: (" + impresora.peek() + ") fue ELIMINADO con éxito");
                    impresora.remove();
                    System.out.println("Pendientes: " + impresora);
                }
                menuImpresora(impresora, scanner);
            }

            case "5": {
                if (!impresora.isEmpty()) {
                    System.err.println("Los archivos: (" + impresora + ") fueron ELIMINADOS con éxito");
                    impresora.clear();
                }
                menuImpresora(impresora, scanner);
            }case "6": {
                menuPrincipal(scanner);
            }
            case "7": {
                finProceso();
            }
            default:
                System.err.println("La opción no es valida");
                menuImpresora(impresora, scanner);
        }
    }

    public static void finProceso() {
        System.out.println("EJECUCIÓN FINALIZADA");
        System.exit(0);
    }
}


