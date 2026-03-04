public class EJ23 {

    /*
     * EJERCICIO:
     * Explora el patrón de diseño "singleton" y muestra cómo crearlo
     * con un ejemplo genérico.
     *
     * DIFICULTAD EXTRA (opcional):
     * Utiliza el patrón de diseño "singleton" para representar una clase que
     * haga referencia a la sesión de usuario de una aplicación ficticia.
     */

    public static void main() {

        // Obtenemos la instancia por primera vez
        Session s1 = Session.getInstance();
        // Al llamar de nuevo, no se crea un objeto nuevo, sino que se devuelve el ya existente
        Session s2 = Session.getInstance();

        User Papalaya = new User("Prometheus", "SunGod", "Prometheus@gmail.com");

        // Al asignar el usuario en s1...
        s1.setUser(Papalaya);

        // ...automáticamente aparece en s2, porque s1 y s2 SON el mismo objeto.
        System.out.println("Usuario en s2: " + s2.getUser().getNombre());

        // Al limpiar la sesión desde una referencia...
        s1.clearSession();

        // ...se limpia para todos los puntos de la aplicación.
        System.out.println("Usuario en s2: " + (s2.getUser() == null));
    }

    // EJERCICIO: Estructura clásica del Singleton
    public static class Singleton {
        // La variable estática que mantendrá la única instancia en memoria
        private static Singleton instance;

        // El constructor PRIVADO es la clave: impide que alguien haga "new Singleton()" desde fuera
        private Singleton() {}

        // El method global de acceso. Si no existe la instancia, la crea; si ya existe, la devuelve.
        public static Singleton getInstance() {
            if (instance == null) {
                instance = new Singleton();
            }
            return instance;
        }
    }

    // EJERCICIO EXTRA: Singleton aplicado a una Sesión
    public static class Session {

        private User user; // Almacena el estado (el usuario logueado)
        private static Session instance;

        // Constructor privado para garantizar la exclusividad
        private Session(){}

        public static Session getInstance() {
            if (instance == null) {
                instance = new Session();
            }
            return instance;
        }

        // Recuperar los datos del usuario (Getter)
        public User getUser() {
            return user;
        }

        @Override
        public String toString() {
            return super.toString();
        }

        // Asignar un usuario a la sesión (Login)
        public void setUser(User user) {
            this.user = user;
        }

        // Borrar los datos de la sesión (Logout)
        public void clearSession() {
            this.user = null;
        }
    }

    /**
     * Clase POJO (Plain Old Java Object) para representar los datos.
     * Esta clase NO es un singleton, porque podemos tener muchos usuarios distintos,
     * pero solo una sesión activa a la vez.
     */
    public static class User {
        private int id;
        private String username;
        private String nombre;
        private String email;

        public User(String username, String nombre, String email) {
            this.id = (int) (Math.random() * 1000);
            this.username = username;
            this.nombre = nombre;
            this.email = email;
        }

        // Getters para recuperar la información del usuario
        public String getEmail() { return email; }
        public String getNombre() { return nombre; }
        public String getUsername() { return username; }
        public int getId() { return id; }
    }
}
