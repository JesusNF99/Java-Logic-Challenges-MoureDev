public class EJ06 {

    public static void main(String[] args) {

        /*
         * EJERCICIO:
         * Entiende el concepto de recursividad creando una función recursiva que imprima
         * números del 100 al 0.
         *
         * DIFICULTAD EXTRA (opcional):
         * Utiliza el concepto de recursividad para:
         * - Calcular el factorial de un número concreto (la función recibe ese número).
         * - Calcular el valor de un elemento concreto (según su posición) en la
         *   sucesión de Fibonacci (la función recibe la posición).
         */


        //Ejercicio
        cuentaAtras(3);

        //Ejercicio extra
        System.out.println(factorial(5));

        //Ejercicio extra 2
        System.out.println(fibonacci(10));
    }

    static int cuentaAtras(int num) {
        if (num == 0) {
            System.out.println(num);
            return 0;
        }
        System.out.println(num);
        return cuentaAtras(num - 1);

    }
    static int factorial(int num) {
        int f = 1;
        if (num == 0) {
            return 1;
        } else {
            return num * factorial(num - 1);
        }
    }
    static int fibonacci(int num) {
        if (num == 0) {
            return 0;
        }
        if (num == 1) {
            return 1;
        }
        return fibonacci(num - 1) + fibonacci(num - 2);

    }



}
