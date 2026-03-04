public class EJ02 {
    /*
     * EJERCICIO:
     * - Crea ejemplos de funciones básicas que representen las diferentes
     *   posibilidades del lenguaje:
     *   Sin parámetros ni retorno, con uno o varios parámetros, con retorno...
     * - Comprueba si puedes crear funciones dentro de funciones.
     * - Utiliza algún ejemplo de funciones ya creadas en el lenguaje.
     * - Pon a prueba el concepto de variable LOCAL y GLOBAL.
     * - Debes hacer print por consola del resultado de todos los ejemplos.
     *   (y tener en cuenta que cada lenguaje puede poseer más o menos posibilidades)
     *
     * DIFICULTAD EXTRA (opcional):
     * Crea una función que reciba dos parámetros de tipo cadena de texto y retorne un número.
     * - La función imprime todos los números del 1 al 100. Teniendo en cuenta que:
     *   - Si el número es múltiplo de 3, muestra la cadena de texto del primer parámetro.
     *   - Si el número es múltiplo de 5, muestra la cadena de texto del segundo parámetro.
     *   - Si el número es múltiplo de 3 y de 5, muestra las dos cadenas de texto concatenadas.
     *   - La función retorna el número de veces que se ha impreso el número en lugar de los textos.
     *
     * Presta especial atención a la sintaxis que debes utilizar en cada uno de los casos.
     * Cada lenguaje sigue unas convenciones que debes de respetar para que el código se entienda.
     */


    // VARIABLE GLOBAL (accesible desde cualquier parte de la clase)
    static final String VARIABLE_GLOBAL = "Soy una variable global de la clase EJ02";

    // FUNCIONES SIN PARÁMETROS Y SIN RETORNO
    public static void variableLocal() {
        String variableLocal = "variablelocal";
        System.out.println(variableLocal);
    }

    public static void cuentaAtras(){
        for (int i = 10; i >= 0; i--) {
            System.out.println(i);
        }
        System.out.println("Despegue!");
    }

   //FUNCIONES CON PARÁMETROS Y SIN RETORNO
    public static void funcionConparSinRet(String nombre) {
        System.out.println("Hola " + nombre + "!");
    }

    public static void funcionConParSinRet2 (int edadActual) {
        int nuevaEdad = edadActual + 5;
        System.out.println("Si ahora tienes " + edadActual + " años, dentro de 5 años tendrás " + nuevaEdad + ".");
    }

    //FUNCIONES SIN PARÁMETROS Y CON RETORNO
    public static String funcionsinparConRet() {
        return "Chicken";
    }

    public static int funcionsinparConRet2() {
        return 11;
    }

//FUNCIONES CON PARÁMETROS Y CON RETORNO
    public static String funcionconCon(int chicken, int chicken2, int chicken3) {
        return "Chicken1, Chicken2, Chicken3";
    }

    public static int multiplicarValores(int num1, int num2) {
        return num1 * num2;
    }

    public static double multiplicarValores(double num1, double num2) {
        return num1 * num2;
    }

    public static int multiplicarValores(int num1, int num2, int num3) {
        return num1 * num2 * num3;
    }

//TAREA EXTRA

    public static int tareaExtra(String st1, String st2) {
        int contador = 0;
        for (int w = 1; w <= 100; w++) {
            if (w % 3 == 0 && w % 5 == 0) {
                System.out.println(st1 + st2);
            } else if (w % 5 == 0) {
                System.out.println(st2);
            } else if (w % 3 ==0) {
                System.out.println(st1);
            } else {
                contador++;
                System.out.println(w);
            }





        }
        return contador;

    }


















}