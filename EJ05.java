import java.util.ArrayList;

public class EJ05 {

    public static void main(String[] args) {
        /*
         * EJERCICIO:
         * - Muestra ejemplos de asignación de variables "por valor" y "por referencia", según
         *   su tipo de dato.
         * - Muestra ejemplos de funciones con variables que se les pasan "por valor" y
         *   "por referencia", y cómo se comportan en cada caso en el momento de ser modificadas.
         * (Entender estos conceptos es algo esencial en la gran mayoría de lenguajes)
         *
         * DIFICULTAD EXTRA (opcional):
         * Crea dos programas que reciban dos parámetros (cada uno) definidos como variables anteriormente.
         * - Cada programa recibe, en un caso, dos parámetros por valor, y en otro caso, por referencia.
         *   Estos parámetros los intercambia entre ellos en su interior, los retorna, y su retorno
         *   se asigna a dos variables diferentes a las originales. A continuación, imprime el valor de las
         *   variables originales y las nuevas, comprobando que se ha invertido su valor en las segundas.
         *   Comprueba también que se ha conservado el valor original en las primeras.
         */

        //Asignación por valor
        /*Un parámetro por valor pasa una copia de la variable a un method y
         opera sobre ella, sin modificar la variable original, ya que trabajamos
         sobre direcciones de memoria diferentes*/
        var a = 5;
        var b = 7;
        System.out.println(a);
        System.out.println(b);
        System.out.println(a + b);


        //Asignación por referencia
        /* Los parámetros por referencia son aquellos parámetros que pasan la
         * dirección de la variable orginal a un method y este actúa sobre ella,
         * modificando el valor interno de la variable referenciada*/





        int[] arr1 = {1,2,3};
        int[] arr2 = arr1;
        imprimirArray(arr1);
        imprimirArray(arr2);
        arr2[0] = 99;
        imprimirArray(arr1);
        imprimirArray(arr2);

        //EJERCICIO EXTRA

        //Programa de intercambio de valores por asignación de valores
        programa1(a, b);
        System.out.println(a);
        System.out.println(b);


        //Programa de intercambio de valores por referencia
        String Prom = "Prometheus";
        String God = "Demigod";

        programa2(Prom, God);

        System.out.println(Prom);
        System.out.println(God);


    }
    public static void programa1(int x, int y) {
        System.out.println(x + " " + y);
        int temp = x;
        x = y;
        y = temp;
        int newValue1 = x;
        int newValue2 = y;
        System.out.println(x + " " + y + " " + newValue1 + " " + newValue2);
    }
    public static void programa2(String x, String y) {
        System.out.println(x + " " + y);
        String temp = x;
        x = y;
        y = temp;
        String newValue1 = x;
        String newValue2 = y;
        System.out.println(x + " " + y + " " + newValue1 + " " + newValue2);

    }


    public static void imprimirArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
            if (i < arr.length - 1)
                System.out.print(", ");
        }
        System.out.println();
    }
}
