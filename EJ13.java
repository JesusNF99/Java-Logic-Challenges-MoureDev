import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EJ13 {
    public static void main(String[] args) {
        /*
         * EJERCICIO:
         * Crea una función que se encargue de sumar dos números y retornar
         * su resultado.
         * Crea un test, utilizando las herramientas de tu lenguaje, que sea
         * capaz de determinar si esa función se ejecuta correctamente.
         *
         * DIFICULTAD EXTRA (opcional):
         * Crea un diccionario con las siguientes claves y valores:
         * "name": "Tu nombre"
         * "age": "Tu edad"
         * "birth_date": "Tu fecha de nacimiento"
         * "programming_languages": ["Listado de lenguajes de programación"]
         * Crea dos test:
         * - Un primero que determine que existen todos los campos.
         * - Un segundo que determine que los datos introducidos son correctos.
         */

        int a = 123;
        int b = 456;
        System.out.println(sumar(a, b));

        //EJERCICIO EXTRA
        cargarDiccionario();






    }

    public static int sumar(int a, int b) {
        return a + b;
    }

    //EJERCICIO EXTRA


    public static Map<String, Object> cargarDiccionario() {
        Map<String, Object> diccionario = new HashMap<>();

        diccionario.put("name", "Prometheus");
        diccionario.put("age", 27);
        diccionario.put("birth_date", "Unknown");
        diccionario.put("programming_languages", Arrays.asList("God", "Demigod", "Human"));

        return diccionario;
    }





}
