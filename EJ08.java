import java.util.ArrayList;

public class EJ08 {

    public static void main(String[] args) {

        /*
         * EJERCICIO:
         * Explora el concepto de clase y crea un ejemplo que implemente un inicializador,
         * atributos y una función que los imprima (teniendo en cuenta las posibilidades
         * de tu lenguaje).
         * Una vez implementada, créala, establece sus parámetros, modifícalos e imprímelos
         * utilizando su función.
         *
         * DIFICULTAD EXTRA (opcional):
         * Implementa dos clases que representen las estructuras de Pila y Cola (estudiadas
         * en el ejercicio número 7 de la ruta de estudio)
         * - Deben poder inicializarse y disponer de operaciones para añadir, eliminar,
         *   retornar el número de elementos e imprimir tod su contenido.
         *
         */



        //Creación de objeto
        Persona Prometheus = new Persona(26, "Prometheus", "Demigod");
        //Asignacion de atributos
        Prometheus.edad = 23;
        //Función para imprimirlos
        Prometheus.mostrarDatos();

        //Cola
        Cola numerosP = new Cola();
        numerosP.anadir(1);
        numerosP.tamano();
        numerosP.imprimir();
        numerosP.anadir(3);
        numerosP.anadir(4);
        numerosP.anadir(5);
        numerosP.anadir(3);
        numerosP.anadir(3);
        numerosP.tamano();
        numerosP.imprimir();
        numerosP.eliminar();
        numerosP.tamano();
        numerosP.imprimir();

        //Pila
        Pila numbersP = new Pila();
        numbersP.anadir(1);
        numbersP.tamano();
        numbersP.imprimir();
        numbersP.anadir(3);
        numbersP.anadir(4);
        numbersP.anadir(5);
        numbersP.anadir(3);
        numbersP.anadir(3);
        numbersP.tamano();
        numbersP.imprimir();
        numbersP.eliminar();
        numbersP.tamano();
        numbersP.imprimir();


    }


    //Creación de clase
    static class Persona {

        //Datos
        int edad;
        String nombre;
        String trabajo;

        //Constructor de la clase
        public Persona (int edad, String nombre, String trabajo) {
            this.edad = edad;
            this.nombre = nombre;
            this.trabajo = trabajo;

        }
        public Persona() {

        }
        //Función para imprimir los datos
        void mostrarDatos() {
            System.out.println("Estos son los datos de " + nombre + ": Edad: " + edad + " Trabajo: " + trabajo + ".");
        }
    }

    //EJERCICIO EXTRA
    public static class Cola {
        //Constructor
        private ArrayList<Integer> elementos = new ArrayList<>();

        public Cola() {
            this.elementos = new ArrayList<>();
        }
        public void anadir(int elemento) {
            elementos.add(elemento);
        }
        public int eliminar() {
            return elementos.remove(0);
        }
        public void tamano() {
            System.out.println("El tamaño de la cola es: " + elementos.size());
        }
        public void imprimir() {
            System.out.println("Los elementos de la cola son: " + elementos);

        }
    }
    public static class Pila {
        //"Constructor"
        private ArrayList<Integer> elementos = new ArrayList<>();

        public Pila() {
            this.elementos = new ArrayList<>();
        }
        public void anadir(int elemento) {

            elementos.add(elemento);
        }
        public int eliminar() {

            return elementos.removeLast();
        }
        public void tamano() {
            System.out.println("El tamaño de la pila es: " + elementos.size());
        }
        public void imprimir() {
            System.out.println("Los elementos de la pila son: " + elementos);

        }
        public int tamamo() {
            return elementos.size();
        }
    }




}
