public class EJ30 {
    /*
     * EJERCICIO:
     * Explora el "Principio SOLID de Inversión de Dependencias (Dependency Inversion
     * Principle, DIP)" y crea un ejemplo simple donde se muestre su funcionamiento
     * de forma correcta e incorrecta.
     */

    /**
     * El Dependency Inversion Principle dice básicamente esto:
     * ❌ Las clases importantes no deberían depender de clases concretas
     * ✅ Deberían depender de interfaces (abstracciones)
     */

    static void main(String[] args) {
        // --- EJERCICIO ---
        Weapon espada = new Sword();
        Warrior g1 = new Warrior(espada);
        g1.attack();

        Weapon arco = new Bow();
        Warrior g2 = new Warrior(arco);
        g2.attack();

        // --- EJERCICIO EXTRA ---
        Notificaciones email = new Email();
        Notificaciones push = new Push();
        Notificaciones sms = new Sms();

        // NOTA TÉCNICA: Aplicamos Inyección de Dependencias. El objeto Usuario
        // recibe la implementación (Email, Push, etc.) ya instanciada desde fuera.
        Usuario u1 = new Usuario(email);
        Usuario u2 = new Usuario(push);
        Usuario u3 = new Usuario(sms);

        u1.send();
        u2.send();
        u3.send();
    }

    // --- EJERCICIO ---
    // Ejemplo incorrecto:
    static class SwordI {
        public void attack() { System.out.println("Sword attack!"); }
    }

    class WarriorI {
        private SwordI weapon = new SwordI();
        public void attack() { weapon.attack(); }
    }

    /* El problema es que el Warrior depende directamente de la Espada 🗡️
     * ¿Qué pasa si mañana quieres usar: un arco 🏹, o magia 🔥?
     * 💥 Tendríamos que modificar la clase Guerrero cada vez
     */

    // Ejemplo correcto:
    interface Weapon {
        void attack();
    }

    static class Sword implements Weapon {
        @Override public void attack() { System.out.println("Sword attack!"); }
    }

    static class Bow implements Weapon {
        @Override public void attack() { System.out.println("Bow attack!"); }
    }

    static class Warrior {
        private Weapon weapon;

        public Warrior(Weapon weapon) {
            this.weapon = weapon;
        }

        public void attack() {
            weapon.attack();
        }
    }
    // ✔ El Guerrero ya no depende de clases concretas
    // ✔ Podemos cambiar el arma sin modificar Guerrero
    // ✔ El código es más flexible
    // ✔ Más fácil de testear

    // --- EJERCICIO EXTRA ---

    /**
     * NOTA TÉCNICA: Se evita el nombre "notify()" en la interfaz porque
     * es un method final reservado en la clase Object de Java para hilos.
     * Usar nombres como "send()" evita colisiones y errores de compilación.
     */
    public interface Notificaciones {
        void send();
    }

    public static class Email implements Notificaciones {
        @Override public void send() {System.out.println("Nuevo Email!");}
    }

    public static class Push implements Notificaciones {
        @Override public void send() {System.out.println("Nueva notificación PUSH!");}
    }

    public static class Sms implements Notificaciones {
        @Override public void send() {System.out.println("Nuevo SMS!");}
    }

    public static class Usuario {
        private Notificaciones notif;

        public Usuario(Notificaciones notif) {
            this.notif = notif;
        }

        /**
         * NOTA TÉCNICA: El method de la clase de alto nivel (Usuario) invoca
         * el method de la abstracción (Notificaciones), delegando la ejecución
         * a la implementación concreta inyectada.
         */
        public void send() {
            notif.send();
        }
    }
}
