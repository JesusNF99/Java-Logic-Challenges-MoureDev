import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;

public class EJ20 {
    public static void main(String[] args) { // Añadido args para firma estándar
        /*
         * EJERCICIO:
         * Utilizando un mecanismo de peticiones HTTP de tu lenguaje, realiza
         * una petición a la web que tú quieras, verifica que dicha petición
         * fue exitosa y muestra por consola el contenido de la web.
         *
         * DIFICULTAD EXTRA (opcional):
         * Utilizando la PokéAPI (https://pokeapi.co), crea un programa por
         * terminal al que le puedas solicitar información de un Pokémon concreto
         * utilizando su nombre o número.
         * - Muestra el nombre, id, peso, altura y tipo(s) del Pokémon
         * - Muestra el nombre de su cadena de evoluciones
         * - Muestra los juegos en los que aparece
         * - Controla posibles errores
         */

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el nombre o ID del Pokémon:");
        String pokemon = scanner.nextLine().toLowerCase(); // ToLowerCase evita errores de URL

        // Construcción dinámica de la URI
        URI url = URI.create("https://pokeapi.co" + pokemon);

        // Configuración de la petición HTTP (Mensajería estándar)
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .GET()
                .build();

        // El cliente es el "navegador" que gestionará el envío
        HttpClient cliente = HttpClient.newHttpClient();

        // EJERCICIO EXTRA: Gestión de múltiples peticiones encadenadas
        HttpResponse<String> response = null;
        try {
            // Primera petición: Datos básicos del Pokémon
            response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Fallo en la conexión principal", e);
        }

        // Verificamos el Status Code 200 (Éxito) antes de procesar el JSON
        if (response.statusCode() == 200) {
            // GSON: Mapeo automático de JSON a Objetos Java (Deserialización)
            Pokemon miPokemon = gson.fromJson(response.body(), Pokemon.class);

            // SEGUNDA PETICIÓN: Obtenemos la URL de la especie para llegar a las evoluciones
            HttpRequest requestSpecies = HttpRequest.newBuilder().uri(URI.create(miPokemon.species.url)).GET().build();
            HttpResponse<String> responseSpecies = null;
            try {
                // El 'scope' de responseSpecies es este bloque IF
                responseSpecies = cliente.send(requestSpecies, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            // Tercera petición: Obtenemos la URL definitiva de la cadena evolutiva
            speciesReply sReply = gson.fromJson(responseSpecies.body(), speciesReply.class);
            HttpRequest requestEvolution = HttpRequest.newBuilder().uri(URI.create(sReply.evolution_chain.url)).GET().build();
            HttpResponse<String> responseEvolution = null;
            try {
                responseEvolution = cliente.send(requestEvolution, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            // JSONPATH: Usamos "$..species.name" para saltar el laberinto recursivo del JSON.
            // Esta ruta busca todos los nombres de especie sin importar la profundidad.
            List<String> evolution = JsonPath.read(responseEvolution.body(), "$..species.name");

            // Impresión de datos básicos (Convertimos unidades: decímetros a metros, etc.)
            System.out.println("Name: " + miPokemon.name);
            System.out.println("Id: " + miPokemon.id);
            System.out.println("Weight: " + (miPokemon.weight * 0.1) + " kg");
            System.out.println("Height: " + (miPokemon.height * 0.1) + " m");

            // Bucle For-Each para recorrer objetos anidados (Lists de GSON)
            for (typeSlot m : miPokemon.types) {
                System.out.println("Type: " + m.type.name);
            }
            for (gameSlot g : miPokemon.game_indices) {
                System.out.println("Game: " + g.version.name);
            }
            // Resultado final de la cadena evolutiva
            System.out.println("Evolution Chain: " + String.join(" -> ", evolution));

        } else {
            // Manejo de errores HTTP (ej: 404 si el Pokémon no existe)
            System.out.println("Error: No se encontró el Pokémon (Status: " + response.statusCode() + ")");
        }
    }

    // CLASES ESPEJO PARA GSON: Deben respetar los nombres de las claves del JSON original.
    public static class Pokemon {
        String name;
        int id;
        int weight;
        int height;
        List<typeSlot> types;
        List<gameSlot> game_indices;
        Species species;
    }

    public static class typeSlot { typeInfo type; }
    public static class typeInfo { String name; }

    public static class gameSlot { gameInfo version; }
    public static class gameInfo { String name; }

    public static class Species { String url; }

    public static class speciesReply { evolution evolution_chain; }
    public static class evolution { String url; }

    private static final Gson gson = new Gson();
}
