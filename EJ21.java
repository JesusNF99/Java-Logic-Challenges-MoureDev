import java.util.concurrent.CompletableFuture;

public class EJ21 {
    /*
     * EJERCICIO:
     * Explora el concepto de callback en tu lenguaje creando un ejemplo
     * simple (a tu elección) que muestre su funcionamiento.
     *
     * DIFICULTAD EXTRA (opcional):
     * Crea un simulador de pedidos de un restaurante utilizando callbacks.
     * Estará formado por una función que procesa pedidos.
     * Debe aceptar el nombre del plato, una callback de confirmación, una
     * de listo y otra de entrega.
     * - Debe imprimir una confirmación cuando empiece el procesamiento.
     * - Debe simular un tiempo aleatorio entre 1 a 10 segundos entre
     *   procesos.
     * - Debe invocar a cada callback siguiendo un orden de procesado.
     * - Debe notificar que el plato está listo o ha sido entregado.
     */
    public static void main(String[] args) {

        // EJERCICIO: Ejemplo de callback síncrono.
        // Pasamos dos bloques de código (lambdas) que el héroe ejecutará al final de su misión.
        Hero hero = new Hero();

        hero.completeQuest(
                (gold) -> {System.out.println("You earned " + gold + " gold!");
                },
                () -> {System.out.println("You failed the quest");
                }
        );

        // EJERCICIO EXTRA: Ejemplo de callbacks asíncronos en paralelo.
        // Al no poner .join() al final de la llamada, 'pedido1' empieza a ejecutarse
        // y el programa continúa inmediatamente a lanzar el 'pedido2'.
        CompletableFuture<Void> pedido1 = simuladorPedidos("Pizza",
                (nombre) -> {System.out.println("Confirmación de pedido: " + nombre);
                },
                (nombre) -> {System.out.println("Pedido listo: " + nombre);
                },
                (nombre) ->{System.out.println("Pedido entregado: " + nombre);
                }
        );

        CompletableFuture<Void> pedido2 = simuladorPedidos("Hamburguesa",
                (nombre) -> {System.out.println("Confirmación de pedido: " + nombre);
                },
                (nombre) -> {System.out.println("Pedido listo: " + nombre);
                },
                (nombre) ->{System.out.println("Pedido entregado: " + nombre);
                }
        );

        // .allOf() actúa como un "director de orquesta".
        // Espera a que ambos hilos (Pizza y Hamburguesa) terminen antes de cerrar el main.
        CompletableFuture.allOf(pedido1, pedido2).join();

    }

    // EJERCICIO
    static class Hero {
        // El method recibe las interfaces que contienen el comportamiento del callback.
        public void completeQuest(QuestReward reward, QuestFailure failure) {
            System.out.println("Quest started");
            int duration = 2000;
            // Lógica interna que decidirá qué callback invocar.
            int survival = (int) (Math.random() * 100);
            try {
                Thread.sleep(duration);
            } catch (RuntimeException | InterruptedException e) {
                e.printStackTrace();
            }

            if (survival >= 50) {
                System.out.println("Quest completed");
                System.out.println("The Hero took " + duration/1000 + " seconds to complete the quest");
                // Invocamos el callback de éxito pasando un dato (gold).
                reward.run(100);
            } else {
                System.out.println("Quest failed");
                // Invocamos el callback de fallo.
                failure.run();
            }
        }
    }

    // Interfaces funcionales personalizadas para los callbacks del héroe.
    interface QuestReward {
        void run(int gold);
    }

    interface QuestFailure {
        void run();
    }

    // EJERCICIO EXTRA: Interfaces para los estados del restaurante.
    interface pedidoConfirmado {
        void run(String nombre);
    }

    interface pedidoListo {
        void run(String nombre);
    }

    interface pedidoEntregado {
        void run(String nombre);
    }

    /**
     * Simulador de pedidos asíncrono.
     * @return CompletableFuture para poder coordinar la finalización fuera de la función.
     */
    public static CompletableFuture<Void> simuladorPedidos(String nombre, pedidoConfirmado confirmacion, pedidoListo listo, pedidoEntregado entregado){
        // runAsync lanza la tarea en un hilo diferente (background).
        return CompletableFuture.runAsync(() -> {
            // Fase 1: Confirmación
            int duration = (int) (Math.random()*10000); // Entre 0 y 10 segundos
            try {
                Thread.sleep(duration);
            }catch (RuntimeException | InterruptedException e) {
                e.printStackTrace();
            }
            confirmacion.run(nombre);

            // Fase 2: Cocinado
            duration = (int) (Math.random()*10000);
            try {
                Thread.sleep(duration);
            }catch (RuntimeException | InterruptedException e) {
                e.printStackTrace();
            }
            listo.run(nombre);

            // Fase 3: Entrega
            duration = (int) (Math.random()*10000);
            try {
                Thread.sleep(duration);
            }catch (RuntimeException | InterruptedException e) {
                e.printStackTrace();
            }
            entregado.run(nombre);
        });
    }
}
