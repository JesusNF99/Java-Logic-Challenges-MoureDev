import java.util.*;
import java.util.stream.IntStream;

public class EJ17 {
    public static void main(String[] args) {

        /*
         * EJERCICIO:
         * Utilizando tu lenguaje, emplea 3 mecanismos diferentes para imprimir
         * números del 1 al 10 mediante iteración.
         *
         * DIFICULTAD EXTRA (opcional):
         * Escribe el mayor número de mecanismos que posea tu lenguaje
         * para iterar valores. ¿Eres capaz de utilizar 5? ¿Y 10?
         */

        // MECANISMO 1: for loop
        // El estándar cuando conocemos el inicio, el fin y el incremento.
        for (int i = 1; i <= 10; i++) {
            System.out.println("for loop " + i);
        }

        // MECANISMO 2: while loop
        // Evalúa la condición ANTES de entrar al bloque.
        int i = 1;
        while (i <= 10) {
            System.out.println("while loop " + i);
            i++;
        }

        // MECANISMO 3: Iterator
        // Mecanismo seguro para recorrer colecciones. Corregido: usamos List para
        // obtener un Iterator de objetos Integer compatible con el bucle.
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Iterator<Integer> iterator = numeros.iterator();

        while (iterator.hasNext()) {
            System.out.println("Iterator " + iterator.next());
        }

        // --- DIFICULTAD EXTRA ---

        // MECANISMO 4: do-while
        // A diferencia del while, este garantiza al menos UNA ejecución
        // porque evalúa la condición DESPUÉS del bloque.
        i = 1;
        do {
            System.out.println("do-while " + i);
            i++;
        } while (i <= 10);

        // MECANISMO 5: for-each (Enhanced for loop)
        // Azúcar sintáctico para recorrer arrays o colecciones sin usar índices.
        for (int numero : numeros) {
            System.out.println("for-each " + numero);
        }

        // MECANISMO 6: forEach (Programación Funcional)
        // Méthod de la interfaz Iterable que utiliza una expresión lambda.
        numeros.forEach(numero -> System.out.println("forEach " + numero));

        // MECANISMO 7: IntStream
        // Introducido en Java 8. rangeClosed genera un flujo de datos eficiente.
        IntStream streamNumeros = IntStream.rangeClosed(1, 10);
        streamNumeros.forEach(numero -> System.out.println("IntStream " + numero));

        // MECANISMO 8: Recursividad
        // Técnica donde la función se invoca a sí misma con un caso base para parar.
        mecanismo8(1);

        // MECANISMO 9: Enumeration (Simulado con Stack)
        // Usamos una estructura LIFO (Last In First Out). Al hacer pop,
        // extraemos y eliminamos elementos hasta que la pila queda vacía.
        Stack<Integer> numerows = new Stack<>();
        numerows.push(10);
        numerows.push(9);
        numerows.push(8);
        numerows.push(7);
        numerows.push(6);
        numerows.push(5);
        numerows.push(4);
        numerows.push(3);
        numerows.push(2);
        numerows.push(1);
        while (!numerows.isEmpty()) {
            System.out.println("Enumeration " + numerows.pop());
        }

        // MECANISMO 10: TimerTask
        // Iteración basada en tiempo. Se ejecuta en un hilo secundario de forma asíncrona.
        cuentaAdelante();
    }

    // MECANISMO 8: Recursividad
    public static int mecanismo8(int z){
        // Caso base para evitar el error StackOverflowError
        if (z == 11) {
            return 0;
        }
        System.out.println("Recursividad " + z);
        return mecanismo8(z + 1);
    }

    // MECANISMO 10: TimerTask
    public static void cuentaAdelante() {
        Timer timer = new Timer();
        // scheduleAtFixedRate programa la tarea repetitiva
        timer.scheduleAtFixedRate(new TimerTask() {
            int contador = 0; // Estado interno de la tarea
            @Override
            public void run() {
                if (contador == 11) {
                    timer.cancel(); // Detiene el hilo del Timer para permitir el cierre del programa
                } else {
                    System.out.println("Timer " + contador);
                    contador++;
                }
            }
        }, 0, 1000); // Se ejecuta cada 1000ms (1 segundo)
    }
}
