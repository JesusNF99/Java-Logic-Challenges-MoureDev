void main() {
    /*
     * ¿Preparad@ para aprender o repasar el lenguaje de programación que tú quieras?
     * - Recuerda que todas las instrucciones de participación están en el
     *   repositorio de GitHub.
     *
     * Lo primero... ¿Ya has elegido un lenguaje?
     * - No todos son iguales, pero sus fundamentos suelen ser comunes.
     * - Este primer reto te servirá para familiarizarte con la forma de participar
     *   enviando tus propias soluciones.
     *
     * EJERCICIO:
     * - Crea un comentario en el código y coloca la URL del sitio web oficial del
     *   lenguaje de programación que has seleccionado.
     * - Representa las diferentes sintaxis que existen de crear comentarios
     *   en el lenguaje (en una línea, varias...).
     * - Crea una variable (y una constante si el lenguaje lo soporta).
     * - Crea variables representando todos los tipos de datos primitivos
     *   del lenguaje (cadenas de texto, enteros, booleanos...).
     * - Imprime por terminal el texto: "¡Hola, [y el nombre de tu lenguaje]!"
     *
     * ¿Fácil? No te preocupes, recuerda que esta es una ruta de estudio y
     * debemos comenzar por el principio.
     */

//1. Comentarios
/*Se pueden escribir de esta manera*/
//No sé más

    String variable = "Esto es una variable"; // Hay que declarar el tipo primero, luego el nombre
    final String CONSTANT = "Esto es una constant"; // Hay que usar final para volverla inmutable y también por
    // convención, el nombre es todo en mayúsculas.

    // Tipos de datos primitivos
    byte byteNumber = 127; // Almacena 1 byte
    short shortNumber = 32767; // Almacena 2 bytes
    int integer = 2147483647; // Almacena 4 bytes
    long longNumber = 922337203685775807L; // Almacena 8 bytes
    float floatNumber = 0.24234f; // Almacena 4 bytes
    double doubleNumber = 0.224982410982; // Almacena 8 bytes
    boolean isBoolean = true; // Almacena 1 bit
    char character = 'a'; // Almacena 2 bytes

    // Tipos de datos no primitivos (Objeto)
    String string = "Soy un string";

    // Imprime por terminal el texto: "¡Hola, [y el nombre de tu lenguaje]
    System.out.println("¡Hola, Java!");
}



