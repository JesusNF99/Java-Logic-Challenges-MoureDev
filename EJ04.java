import java.util.Arrays;

public class EJ04 {

    public static void main(String[] args){

        /*
         * EJERCICIO:
         * Muestra ejemplos de todas las operaciones que puedes realizar con cadenas de caracteres
         * en tu lenguaje. Algunas de esas operaciones podrían ser (busca todas las que puedas):
         * - Acceso a caracteres específicos, subcadenas, longitud, concatenación, repetición, recorrido,
         *   conversión a mayúsculas y minúsculas, reemplazo, división, unión, interpolación, verificación...
         *
         * DIFICULTAD EXTRA (opcional):
         * Crea un programa que analice dos palabras diferentes y realice comprobaciones
         * para descubrir si son:
         * - Palíndromos
         * - Anagramas
         * - Isogramas
         */


        String cadena = "Prometheus";
        String god = "Demigod";

        //Concatenación
        System.out.println(cadena + god);

        //Repetición
        System.out.println("Prometheus is a " + god.repeat(3));

        //Indexación
        System.out.println("" + god.charAt(0) + god.charAt(2) + god.charAt(3) + god.charAt(4)); //Las comillas son necesarias para que el sistema coja los valores como cadenas de texto y no como números de Unicode

        //Longitud
        System.out.println(god.length());

        //Slicing
        System.out.println(god.substring(0, 3));

        //Búsqueda
        System.out.println(god.indexOf("o"));

        //Reemplazo
        System.out.println(god.replace("do", "da"));

        //Division
        System.out.println(Arrays.toString(god.split("r")));

        //Mayusculas y minusculas
        System.out.println(god.toUpperCase());
        System.out.println(god.toLowerCase());

        //Eliminacion de espacios
        System.out.println(god.trim());

        //Busqueda al primcipio y final
        System.out.println(cadena.startsWith("Pro"));
        System.out.println(cadena.endsWith("s"));

        //Busqueda de ocurrencias
        System.out.println(cadena.toLowerCase().indexOf("a"));

        //Formateo
        System.out.printf("%s, es un %s%n", cadena, god);

        //Interpolación
        System.out.println("Creo que " + cadena + " es un " + god);

        //Transformacion en lista de caracteres
        System.out.println(Arrays.toString(cadena.toCharArray()));

        //Transformacion de lista en cadena
        System.out.println(String.valueOf(cadena.toCharArray()));

        //Transformaciones numericas
        var numero = 0;
        numero = 13;
        System.out.println(numero);
        System.out.println(Integer.parseInt(String.valueOf(numero)));

        //Comprobaciones varias
        numero = 1234124214;

        System.out.println(cadena.matches("[a-zA-Z0-9]+")); // isalnum
        System.out.println(god.matches("[a-zA-Z]+"));    // isalpha
        System.out.println(cadena.matches("[a-zA-Z]+"));    // isalpha
        System.out.println(god.matches("[0-9]+"));


        //EJERCICIO EXTRA
        var Palabra1 = "";
        var Palabra2 = "";
        var sc2 = new java.util.Scanner(System.in);

        do {
            System.out.println("Introduce la primera palabra");
            Palabra1 = sc2.nextLine().trim().toLowerCase();
            System.out.println("Introduce la segunda palabra");
            Palabra2 = sc2.nextLine().trim().toLowerCase();
            if (Palabra2.length() != Palabra1.length()) {
                System.out.println("Las palabras no son del mismo tamaño");
                break;
            }
            if (Palabra2.equals(Palabra1)) {
                System.out.println("Las palabras son iguales");
                break;
            }else {
                //Comprobar si es palíndromo
                if (new StringBuilder(Palabra1).reverse().toString().equals(Palabra2)) {
                    System.out.println("Las palabras son palíndromos");
                }
                //Comprobar si son anagramas
                char[] c1 = Palabra1.toCharArray();
                char[] c2 = Palabra2.toCharArray();
                Arrays.sort(c1);
                Arrays.sort(c2);
                if (Arrays.equals(c1, c2)) {
                    System.out.println("Las palabras son anagramas");

                }
                //Comprobar si son isogramas
                if (Palabra1.chars().distinct().count() == Palabra1.length()) {
                    System.out.println("La primera palabra es un isograma");
                }
                if (Palabra2.chars().distinct().count() == Palabra2.length()) {
                    System.out.println("La segunda palabra es un isograma");
                }


            }







        } while (true);

    }
}
