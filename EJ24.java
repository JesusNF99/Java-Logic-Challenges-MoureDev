public class EJ24 {
    /*
     * EJERCICIO:
     * Explora el concepto de "decorador" y muestra cómo crearlo
     * con un ejemplo genérico.
     *
     * DIFICULTAD EXTRA (opcional):
     * Crea un decorador que sea capaz de contabilizar cuántas veces
     * se ha llamado a una función y aplícalo a una función de tu elección.
     */

    public static void main(String[] args) {
        // EJERCICIO: El Café
        Coffee myCoffee = new SimpleCoffee();
        myCoffee = new WithMilk(myCoffee); // Envolvemos el café simple en leche
        myCoffee = new WithSugar(myCoffee); // Envolvemos el resultado anterior en azúcar

        System.out.println("Coste: " + myCoffee.getCost());
        System.out.println("Descripción: " + myCoffee.getDescription());

        // EJERCICIO EXTRA: La Pelota
        Pelota pelotaRoja = new PelotaSimple();
        // Decoramos la pelota con el contador.
        // ¡OJO! Guardarla en una variable tipo 'Pelota' oculta los métodos nuevos.
        pelotaRoja = new ContadorPelota(pelotaRoja);

        pelotaRoja.lanzar();
        pelotaRoja.lanzar();
        pelotaRoja.lanzar();

        // DUDA SOLUCIONADA: Al haber declarado 'pelotaRoja' como interfaz 'Pelota',
        // Java no "ve" el method getLanzamientos(). Necesitamos un CASTING (ContadorPelota)
        // para decirle al compilador: "Trata este objeto como el decorador que es".
        System.out.println("Pelotas lanzadas: " + ((ContadorPelota) pelotaRoja).getLanzamientos());
    }

    // --- CONCEPTOS TEÓRICOS ---

    // 1. INTERFACE: Usamos 'implements' porque la interfaz es un CONTRATO.
    // Dice QUÉ debe hacer la clase (lanzar), pero no CÓMO.
    interface Coffee {
        double getCost();
        String getDescription();
    }

    public static class SimpleCoffee implements Coffee {
        public double getCost() { return 2.0; }
        public String getDescription() { return "Café simple"; }
    }

    // 2. ABSTRACT: ¿Por qué la clase decoradora es abstracta?
    // Porque no queremos que nadie cree un "Decorador" a secas; no tendría sentido.
    // Solo sirve como plantilla para los decoradores reales (leche, azúcar).
    abstract static class CoffeeDecorator implements Coffee {

        // 3. PROTECTED: ¿Por qué no private?
        // 'private' lo ocultaría incluso para sus hijos (WithMilk).
        // 'protected' permite que los decoradores concretos accedan directamente
        // al objeto que están envolviendo sin usar getters complicados.
        protected Coffee decoratedCoffee;

        public CoffeeDecorator(Coffee coffee) {
            this.decoratedCoffee = coffee;
        }

        public double getCost() {
            return decoratedCoffee.getCost();
        }

        public String getDescription() {
            return decoratedCoffee.getDescription();
        }
    }

    // 4. EXTENDS: Usamos 'extends' porque estas clases HEREDAN el comportamiento
    // base de la clase abstracta y solo añaden su pequeña "capa" extra.
    static class WithMilk extends CoffeeDecorator {
        public WithMilk(Coffee coffee) { super(coffee); }

        public double getCost() {
            return super.getCost() + 0.5; // Llama al coste acumulado y suma
        }

        public String getDescription() {
            return super.getDescription() + ", con leche";
        }
    }

    static class WithSugar extends CoffeeDecorator {
        public WithSugar(Coffee coffee) { super(coffee); }

        public double getCost() {
            return super.getCost() + 0.2;
        }

        public String getDescription() {
            return super.getDescription() + ", con azúcar";
        }
    }

    // --- DIFICULTAD EXTRA ---

    interface Pelota {
        void lanzar();
    }

    public static class PelotaSimple implements Pelota {
        public void lanzar() {
            System.out.println("Pelota lanzada");
        }
    }

    abstract static class PelotaDecorator implements Pelota {
        protected Pelota decoratedPelota;

        public PelotaDecorator(Pelota pelota) {
            this.decoratedPelota = pelota;
        }

        public void lanzar() {
            decoratedPelota.lanzar();
        }
    }

    static class ContadorPelota extends PelotaDecorator {
        private int lanzamientos = 0;

        public ContadorPelota (Pelota lanzar) {
            super(lanzar);
        }

        @Override
        public void lanzar() {
            lanzamientos++; // Funcionalidad extra: CONTAR
            super.lanzar(); // Ejecutamos la acción original de la pelota
        }

        public int getLanzamientos() {
            return lanzamientos;
        }
    }
}
