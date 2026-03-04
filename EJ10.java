import java.io.FileReader;
import java.io.IOException;

public class EJ10 {
    public static void main(String[] args) throws Exception {

        /*
         * EJERCICIO:
         * Explora el concepto de manejo de excepciones según tu lenguaje.
         * Fuerza un error en tu código, captura el error, imprime dicho error
         * y evita que el programa se detenga de manera inesperada.
         * Prueba a dividir "10/0" o acceder a un índice no existente
         * de un listado para intentar provocar un error.
         *
         * DIFICULTAD EXTRA (opcional):
         * Crea una función que sea capaz de procesar parámetros, pero que también
         * pueda lanzar 3 tipos diferentes de excepciones (una de ellas tiene que
         * corresponderse con un tipo de excepción creada por nosotros de manera
         * personalizada, y debe ser lanzada de manera manual) en caso de error.
         * - Captura todas las excepciones desde el lugar donde llamas a la función.
         * - Imprime el tipo de error.
         * - Imprime si no se ha producido ningún error.
         * - Imprime que la ejecución ha finalizado.
         */

        //Para capturar errores, hacemos bloques try/catch

        //Tipos de excepciones en Java:
        //Checked Exception - Excepción que se provoca al compilar, es decir, estamos obligados a tratarlas - IOException
        try {
            FileReader fr = new FileReader("StandardFile.txt");
        } catch (IOException i) {
            System.out.println("No se pudo acceder al archivo");

        }

        //Unchecked Exception - Excepción que se provoca al correr el programa - NullPointerException
        try {
            //Código que puede causarnos problemas
            int myInt = Integer.parseInt("Pants");
        }
        catch (NumberFormatException nfe) {
            //Código que se ejecuta si el anterior da problemas
            System.out.println("No puedes hacer eso" + nfe.getMessage());
        }
        finally {
            System.out.println("Prometeo alcanza el sol");
        }

        //Error - Es irrecuperable, no es nada recomendable lanzar un catch a un error - OutOfMemoryError

        //EJERCICIO EXTRA
        validarnumero(27);

    }

    //1. Creacion de Excepción perosnalizada
    public static class numeroinferioraceroException extends Exception {
        public numeroinferioraceroException(String message) {
            super(message);
        }
    }

    //2. Creación de Method Personalizado
    private static void validarnumero(int numero) throws Exception {

        if (numero < 18) {
            throw new numeroinferioraceroException("Menor que 18");
        }
        if (numero > 15) {
            throw new Exception ("Mayor que 15");
        }
        if (numero == 10) {
            throw new numeroinferioraceroException("Voy a beber " + numero + " cocacolas");
        }
        if (numero < 7) {
            System.out.println("Menor que 7");
        }

    }
}
