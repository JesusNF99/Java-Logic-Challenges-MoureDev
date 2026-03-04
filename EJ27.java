public class EJ27 {
    /*
     * EJERCICIO:
     * Explora el "Principio SOLID Abierto-Cerrado (Open-Close Principle, OCP)"
     * y crea un ejemplo simple donde se muestre su funcionamiento
     * de forma correcta e incorrecta.
     *
     * DIFICULTAD EXTRA (opcional):
     * Desarrolla una calculadora que necesita realizar diversas operaciones matemáticas.
     * Requisitos:
     * - Debes diseñar un sistema que permita agregar nuevas operaciones utilizando el OCP.
     * Instrucciones:
     * 1. Implementa las operaciones de suma, resta, multiplicación y división.
     * 2. Comprueba que el sistema funciona.
     * 3. Agrega una quinta operación para calcular potencias.
     * 4. Comprueba que se cumple el OCP.
     */

    public static void main(String[] args) {
        // --- PRUEBA DEL EJEMPLO INCORRECTO ---
        // Si queremos un café nuevo, tendríamos que modificar la clase 'Cafe' añadiendo más 'else if'.
        Cafe cafeMalo = new Cafe("Capuccino", 2);
        cafeMalo.tomarCafe();

        // --- PRUEBA DEL EJEMPLO CORRECTO ---
        // La 'Cafeteria' no cambia, solo le pasamos nuevas implementaciones de 'ICafe'.
        Cafeteria miCafeteria = new Cafeteria();
        ICafe capu = new Capuccino("Capuccino");
        miCafeteria.servir(capu);

        // --- EJERCICIO EXTRA: CALCULADORA OCP ---
        Calculadora calc = new Calculadora();

        // 1. Probamos operaciones básicas
        Operate suma = new Suma(15, 5);
        // DUDA SOLUCIONADA: Cambiamos Calculate de 'void' a 'double' para poder imprimir el retorno.
        System.out.println("Resultado suma: " + calc.Calculate(suma));

        // 2. Probamos la extensión obligatoria (Potencia)
        Operate pot = new Potencia(2, 3);
        System.out.println("Resultado potencia: " + calc.Calculate(pot));

        // 3. Probamos el Factorial
        Operate fact = new Factorial(5);
        System.out.println("Resultado factorial de 5: " + calc.Calculate(fact));
    }

    // --- EJERCICIO: CAFÉ ---

    // INCORRECTO: "Cerrado a la extensión". Cada vez que hay una bebida nueva, hay que editar esta clase.
    public static class Cafe {
        private String cafe;
        private int precio;

        public Cafe(String cafe, int precio) {
            this.cafe = cafe;
            this.precio = precio;
        }

        public void tomarCafe() {
            if (cafe.equals("Capuccino")) {
                System.out.println("Me estoy tomando un " + cafe);
            } else if (cafe.equals("Frapuccino")) {
                System.out.println("Me estoy tomando un " + cafe);
            } else if (cafe.equals("Té")) {
                System.out.println("Me estoy tomando un " + cafe);
            }
        }
    }

    // CORRECTO: Definimos un contrato (Interfaz).
    public interface ICafe {
        void tomarCafe();
    }

    public static class Capuccino implements ICafe {
        private String nombre;
        public Capuccino(String nombre) { this.nombre = nombre; }
        @Override
        public void tomarCafe() { System.out.println("Me estoy tomando un rico " + nombre); }
    }

    // El "Motor" cumple el OCP: recibe la interfaz y no le importa qué café sea.
    public static class Cafeteria {
        public void servir(ICafe cafe) { cafe.tomarCafe(); }
    }

    // --- EJERCICIO EXTRA: CALCULADORA ---

    /**
     * Esta clase está CERRADA a modificaciones.
     * No importa si mañana inventas la raíz cuadrada; no tocarás este código.
     */
    public static class Calculadora {
        // DUDA SOLUCIONADA: El method debe devolver double para que el main pueda usar el dato.
        public double Calculate(Operate operation){
            return operation.operate();
        }
    }

    // Interfaz que permite la EXTENSIÓN (Abierta)
    public interface Operate {
        double operate();
    }

    public static class Suma implements Operate {
        private int num1, num2;
        // DUDA SOLUCIONADA: Añadimos constructores para pasar los datos a la operación.
        public Suma(int num1, int num2) { this.num1 = num1; this.num2 = num2; }
        @Override
        public double operate() { return num1 + num2; }
    }

    public static class Division implements Operate {
        private int num1, num2;
        public Division(int num1, int num2) { this.num1 = num1; this.num2 = num2; }
        @Override
        public double operate() {
            // Hacemos cast a double para no perder decimales
            return (double) num1 / num2;
        }
    }

    public static class Factorial implements Operate {
        private int num1;
        public Factorial(int num1){ this.num1 = num1; }
        @Override
        public double operate() {
            int result = 1;
            // DUDA SOLUCIONADA: Usamos n <= num1 para incluir el último número en el cálculo.
            for (int n = 1; n <= num1; n++) {
                result *= n;
            }
            return result;
        }
    }

    // Requisito 3: Quinta operación agregada cumpliendo el OCP
    public static class Potencia implements Operate {
        private int num1, num2;
        public Potencia(int num1, int num2){ this.num1 = num1; this.num2 = num2; }
        @Override
        public double operate() {
            return Math.pow(num1, num2);
        }
    }
}
