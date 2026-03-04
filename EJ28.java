public class EJ28 {

    /*
     * EJERCICIO:
     * Explora el "Principio SOLID de Sustitución de Liskov (Liskov Substitution Principle, LSP)"
     * y crea un ejemplo simple donde se muestre su funcionamiento
     * de forma correcta e incorrecta.
     *
     * DIFICULTAD EXTRA (opcional):
     * Crea una jerarquía de vehículos. Todos ellos deben poder acelerar y frenar, así como
     * cumplir el LSP.
     * Instrucciones:
     * 1. Crea la clase Vehículo.
     * 2. Añade tres subclases de Vehículo.
     * 3. Implementa las operaciones "acelerar" y "frenar" como corresponda.
     * 4. Desarrolla un código que compruebe que se cumple el LSP.
     */

    /**
     * LISKOV SUBSTITUTION PRINCIPLE (LSP)
     * "Si S es un subtipo de T, entonces los objetos de tipo T pueden ser sustituidos por
     * objetos de tipo S sin alterar las propiedades del programa".
     *
     * Condiciones clave:
     * 1. Firma de métodos: Los argumentos y tipos de retorno deben ser compatibles.
     * 2. Precondiciones: Una subclase no puede exigir más que su padre.
     * 3. Postcondiciones: Una subclase no puede devolver menos que su padre.
     * 4. Invariantes: Se deben mantener las condiciones de estado de la superclase.
     * 5. Excepciones: No se pueden lanzar nuevas excepciones que el padre no contemple.
     */


    public static void main(String[] args) { // Nota: Añadido String[] args para que sea un main válido

        // EJERCICIO
        Car2 Coche2 = new Car2();
        Coche2.startEngine();
        Bicycle2 Bicicleta2 = new Bicycle2();
        Bicicleta2.startVehicle();

        // EJERCICIO EXTRA
        // Aquí aplicamos el LSP: Tratamos a los objetos por su tipo base.
        vehicleExtra coche = new CarExtra();
        vehicleExtra camion = new TruckExtra();
        vehicleExtra bus = new BusExtra();

        // Demostración de sustitución: Cualquier subclase de vehicleExtra
        // puede pasar por este method sin romper la lógica.
        Executer(coche);
        Executer(camion);
        Executer(bus);
    }

    // --- EJERCICIO ---

    // Forma incorrecta:
    public interface Vehicle {
        void startEngine();
    }

    public static class Car implements Vehicle {
        public void startEngine() {
            System.out.println("Starting a car!");
        }
    }

    /**
     * Violación del LSP: Lanzar UnsupportedOperationException es un cambio
     * inesperado en el comportamiento que el cliente de 'Vehicle' no espera.
     */
    public static class Bicycle implements Vehicle {
        public void startEngine() {
            throw new UnsupportedOperationException("Bicycle doesn't have an engine!");
        }
    }

    // Forma correcta:
    // Separamos responsabilidades para que ninguna clase herede métodos que no puede cumplir.
    public interface noEngineVehicle {
        void startVehicle();
    }

    public interface engineVehicle {
        void startEngine();
    }

    public static class Car2 implements engineVehicle {
        public void startEngine() {
            System.out.println("Starting a car!");
        }
    }


    public static class Bicycle2 implements noEngineVehicle {
        public void startVehicle() {
            System.out.println("Bicycle started to run!");
        }
    }

    // --- EJERCICIO EXTRA ---

    /**
     * PUNTO CLAVE LSP:
     * Usamos una clase abstracta como base. Todas las subclases
     * GARANTIZAN que saben acelerar y frenar.
     */
    public abstract static class vehicleExtra {
        abstract void accelerate();
        abstract void brake();
    }

    public static class CarExtra extends vehicleExtra {
        @Override
        public void accelerate() { System.out.println("Car accelerating!"); }
        @Override
        public void brake() { System.out.println("Car braking!"); }
    }

    public static class TruckExtra extends vehicleExtra {
        @Override
        public void accelerate() { System.out.println("Truck accelerating!"); }
        @Override
        public void brake() { System.out.println("Truck braking!"); }
    }

    public static class BusExtra extends vehicleExtra {
        @Override
        public void accelerate() { System.out.println("Bus accelerating!"); }
        @Override
        public void brake() { System.out.println("Bus braking!"); }
    }

    /**
     * PRUEBA DE FUEGO LSP:
     * Este method cumple el principio porque utiliza la abstracción 'vehicleExtra'.
     * Si mañana añades una clase 'MotorcycleExtra', este method seguirá funcionando
     * sin cambiar ni una sola línea de código.
     */
    public static void Executer(vehicleExtra vehicle) {
        vehicle.accelerate();
        vehicle.brake();
    }
}
