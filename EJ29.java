public class EJ29 {
    /*
     * EJERCICIO:
     * Explora el "Principio SOLID de Segregación de Interfaces (Interface Segregation Principle, ISP)"
     * y crea un ejemplo simple donde se muestre su funcionamiento de forma correcta e incorrecta.
     *
     * DIFICULTAD EXTRA (opcional):
     * Crea un gestor de impresoras.
     * Requisitos:
     * 1. Algunas impresoras sólo imprimen en blanco y negro.
     * 2. Otras sólo a color.
     * 3. Otras son multifunción, pueden imprimir, escanear y enviar fax.
     * Instrucciones:
     * 1. Implementa el sistema, con los diferentes tipos de impresoras y funciones.
     * 2. Aplica el ISP a la implementación.
     * 3. Desarrolla un código que compruebe que se cumple el principio.
     */

    /**
     * INTERFACE SEGREGATION PRINCIPLE (ISP)
     * Establece que una clase no debe ser obligada a implementar interfaces que no utiliza.
     * Es mejor tener muchas interfaces específicas que una sola interfaz generalista.

     * Condiciones:
     * 1. Cohesión: Los métodos de una interfaz deben estar estrechamente relacionados.
     * 2. Desacoplamiento: El cliente solo conoce los métodos que realmente necesita.
     * 3. Flexibilidad: Facilita la composición de capacidades (como hiciste con ImpresoraPerfecta).
     */

    public static void main(String[] args) {
        // EJERCICIO EXTRA - Comprobación de cumplimiento
        // Aquí demostramos que cada objeto solo tiene acceso a lo que realmente puede hacer.

        ImpresoraPerfecta impresoraPF = new ImpresoraPerfecta();
        impresoraPF.imprimirBN();
        impresoraPF.enviarFax(); // Capacidad específica de multifunción

        Impresora1 impresoraBN = new Impresora1();
        impresoraBN.imprimirBN();
        // impresoraBN.enviarFax(); // Esto daría error de compilación, lo cual es CORRECTO por ISP.

        Impresora2 impresoraColor = new Impresora2();
        impresoraColor.imprimirColor();
    }

    // --- EJERCICIO ---

    /**
     * EJEMPLO INCORRECTO
     * DUDA RESUELTA: El error aquí es la "interfaz gorda". Al incluir search(),
     * obligamos a PaperBackBookI a implementar un comportamiento digital.
     */
    public interface Book {
        void open(); void close(); void read(); void bookmark(); void search();
    }

    public class PaperBackBookI implements Book {
        @Override public void open() {}

        @Override public void close() {}

        @Override public void read() {}

        @Override public void bookmark() {}

        @Override public void search() {}
    }

    public class AudioBookI implements Book {
        @Override public void open() {}

        @Override public void close() {}

        @Override public void read() {}

        @Override public void bookmark() {}

        @Override public void search() {}
    }

    /**
     * EJEMPLO CORRECTO
     * Creamos 'Readable' para lo común
     * y 'Searchable' para la funcionalidad extra.
     */
    public interface Readable {
        void open(); void close(); void read(); void bookmark();
    }

    public interface Searchable {
        void search();
    }

    // El libro físico solo implementa lo que puede cumplir.
    public class PaperBackBookC implements Readable {
        @Override public void open() {}
        @Override public void close() {}
        @Override public void read() {}
        @Override public void bookmark() {}
    }

    // El audiolibro compone ambas interfaces porque tiene ambas capacidades.
    public class AudioBookC implements Readable, Searchable {
        @Override public void open() {}
        @Override public void close() {}
        @Override public void read() {}
        @Override public void bookmark() {}
        @Override public void search() {}
    }

    // --- EJERCICIO EXTRA ---

    /**
     * APLICACIÓN DEL ISP:
     * Dividimos las capacidades en 3 interfaces atómicas.
     * Esto permite "armar" impresoras a medida.
     */
    public interface ImpresoraBN { void imprimirBN(); }
    public interface ImpresoraColor { void imprimirColor(); }
    public interface ImpresoraFunciones { void escanear(); void enviarFax(); }

    public static class Impresora1 implements ImpresoraBN {
        @Override
        public void imprimirBN() { System.out.println("Imprimiendo en blanco y negro"); }
    }

    public static class Impresora2 implements ImpresoraColor {
        @Override
        public void imprimirColor() { System.out.println("Imprimiendo en color"); }
    }

    /**
     * COMPOSICIÓN:
     * La ImpresoraPerfecta es el ejemplo de que el ISP permite
     * múltiples herencias de interfaz para crear objetos complejos
     * sin ensuciar a los objetos simples (Impresora1).
     */
    public static class ImpresoraPerfecta implements ImpresoraFunciones, ImpresoraColor, ImpresoraBN {
        @Override public void imprimirBN() { System.out.println("Imprimiendo en B/N"); }
        @Override public void imprimirColor() { System.out.println("Imprimiendo en color"); }
        @Override public void escanear() { System.out.println("Escaneando"); }
        @Override public void enviarFax() { System.out.println("Enviando fax"); }
    }
}