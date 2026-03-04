import java.util.ArrayList;

public class EJ09 {
    public static void main(String[] args) {
        /*
         * EJERCICIO:
         * Explora el concepto de herencia según tu lenguaje. Crea un ejemplo que
         * implemente una superclase Animal y un par de subclases Perro y Gato,
         * junto con una función que sirva para imprimir el sonido que emite cada Animal.
         *
         * DIFICULTAD EXTRA (opcional):
         * Implementa la jerarquía de una empresa de desarrollo formada por Empleados que
         * pueden ser Gerentes, Gerentes de Proyectos o Programadores.
         * Cada empleado tiene un identificador y un nombre.
         * Dependiendo de su labor, tienen propiedades y funciones exclusivas de su
         * actividad, y almacenan los empleados a su cargo.
         */

        Animal animal = new Animal();
        animal.emitirSonido();
        Perro perro = new Perro();
        perro.emitirSonido();
        Gato gato = new Gato();
        gato.emitirSonido();

        //EJERCICIO EXTRA
        Programador JackP = new Programador(123,"Jack");
        Gerente JohnG = new Gerente(123123, "John");
        GerenteProyectos FayeGP = new GerenteProyectos(1345, "Faye");

        JackP.programar();
        JohnG.gestionarEquipo();
        FayeGP.gestionarEquipo();
        FayeGP.addEmpleado(JohnG);
        FayeGP.addEmpleado(JackP);
        FayeGP.gestionarEquipo();
    }


    public static class Animal {

        public void emitirSonido() {
            System.out.println("Sonido de animal");
        }
    }

    public static class Perro extends Animal {
        @Override public void emitirSonido() {
            System.out.println("Guau");
        }

    }
    public static class Gato extends Animal {
        @Override public void emitirSonido() {
            System.out.println("Miau");
        }
    }


    //EJERCICIO EXTRA
    public static class Empleado {
        int ID;
        String nombre;

        //Constructor
        public Empleado(int ID, String nombre) {
            this.ID = ID;
            this.nombre = nombre;
        }


    }

    public static class Gerente extends Empleado {

        ArrayList<Empleado> empleados = new ArrayList<>();

        //Constructor
        public Gerente(int ID, String nombre) {
            super(ID, nombre);
        }

        //Funcion
        public void gestionarEquipo() {
            System.out.println("Tengo a mi cargo a: ");
            for (Empleado empleado : empleados) {
                System.out.println(empleado.nombre);
            }
        }

        //Exclusive method
        public void addEmpleado(Empleado nuevoEmpleado) {
            empleados.add(nuevoEmpleado);
        }
    }

    public static class Programador extends Empleado {

        //Constructor
        public Programador(int ID, String nombre) {
            super(ID, nombre);
        }


        //Funcion
        public void programar() {
            System.out.println(nombre + ": " + "Estoy programando");
        }
    }
    public static class GerenteProyectos extends Gerente {

        //Constructor
        public GerenteProyectos(int ID, String nombre) {
            super(ID, nombre);
        }
    }

}
