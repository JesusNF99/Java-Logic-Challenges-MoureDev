import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

public class EJ15 {
    static void main(String[] args) {
        /*
         * EJERCICIO & DIFICULTAD EXTRA:
         * Coordinación de tareas asíncronas A, B, C y D.
         */

        // Lanzamos A, B y C sin usar .join() todavía.
        // Al no poner el .join() aquí, las tres empiezan su "cuenta atrás"
        // simultáneamente en hilos separados (Paralelismo).
        CompletableFuture<Void> fA = funcionAsincrona("A", 1);
        CompletableFuture<Void> fB = funcionAsincrona("B", 2);
        CompletableFuture<Void> fC = funcionAsincrona("C", 3);

        // Esta es la "barrera de control".
        // .allOf crea una super-tarea que engloba a las tres.
        // El .join() bloquea el hilo principal (main) para que no siga avanzando
        // hasta que la más lenta (C) haya terminado de imprimir su mensaje.
        CompletableFuture.allOf(fA, fB, fC).join();

        // Ahora que la barrera se ha abierto, lanzamos la D.
        // Importante: Si la hubiéramos declarado arriba con las otras,
        // habría empezado a la vez que A, B y C. Al ponerla aquí,
        // garantizamos que D solo "nace" cuando las otras han muerto.
        CompletableFuture<Void> fD = funcionAsincrona("D", 1);

        // Necesitamos este último .join() porque CompletableFuture usa hilos tipo 'Daemon'.
        // Si no esperamos a que D termine, el programa main llegaría al final de las llaves
        // y cerraría la aplicación antes de que D pudiera imprimir su resultado.
        fD.join();
    }

    /**
     * Función asíncrona parametrizada.
     * Cambiamos el tipo de retorno a CompletableFuture<Void> para poder usar
     * métodos de coordinación como .join() o .allOf().
     */
    public static CompletableFuture<Void> funcionAsincrona(String nombre, int segundos) {

        // Este mensaje está fuera del runAsync. Se ejecuta inmediatamente
        // en el hilo principal cuando llamamos a la función.
        System.out.println("Esta función tardará " + segundos + " segundos y se llamará " + nombre);

        // Retornamos el "ticket" (CompletableFuture).
        // Tod0 lo que está dentro de las llaves del lambda ( () -> { ... }...)
        // se ejecutará en un hilo distinto al principal.
        return CompletableFuture.runAsync(() -> {
            // Capturamos el tiempo de inicio DENTRO del hilo para mayor precisión.
            LocalDateTime initialDate = LocalDateTime.now();

            try {
                // Thread.sleep pide milisegundos, por eso multiplicamos por 1000.
                // Usamos la 'L' para manejar el número como tipo Long.
                Thread.sleep(segundos * 1000L);
            } catch (InterruptedException e) {
                // Obligatorio capturar esta excepción si usamos sleep.
                throw new RuntimeException(e);
            }

            // Capturamos el tiempo final DESPUÉS de la siesta (sleep).
            // Si lo hiciéramos antes, la diferencia siempre sería 0.
            LocalDateTime finalDate = LocalDateTime.now();

            // Calculamos la duración real usando la clase Duration.
            // .getSeconds() nos da el número entero de segundos transcurridos.
            System.out.println("Esta función ha tardado: " +
                    (Duration.between(initialDate, finalDate).getSeconds()) +
                    " segundos y " + nombre + " ha finalizado.");
        });
    }
}
