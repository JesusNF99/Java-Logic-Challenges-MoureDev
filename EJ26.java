import java.util.ArrayList;
import java.util.List;

public class EJ26 {
    /*
     * EJERCICIO:
     * Explora el "Principio SOLID de Responsabilidad Única (Single Responsibility
     * Principle, SRP)" y crea un ejemplo simple donde se muestre su funcionamiento
     * de forma correcta e incorrecta.
     *
     * DIFICULTAD EXTRA (opcional):
     * Desarrolla un sistema de gestión para una biblioteca. El sistema necesita
     * manejar diferentes aspectos como el registro de libros, la gestión de usuarios
     * y el procesamiento de préstamos de libros.
     * Requisitos:
     * 1. Registrar libros: El sistema debe permitir agregar nuevos libros con
     * información básica como título, autor y número de copias disponibles.
     * 2. Registrar usuarios: El sistema debe permitir agregar nuevos usuarios con
     * información básica como nombre, número de identificación y correo electrónico.
     * 3. Procesar préstamos de libros: El sistema debe permitir a los usuarios
     * tomar prestados y devolver libros.
     * Instrucciones:
     * 1. Diseña una clase que no cumple el SRP: Crea una clase Library que maneje
     * los tres aspectos mencionados anteriormente (registro de libros, registro de
     * usuarios y procesamiento de préstamos).
     * 2. Refactoriza el código: Separa las responsabilidades en diferentes clases
     * siguiendo el Principio de Responsabilidad Única.
     */

    public static void main(String[] args) {
        // En un sistema real, aquí instanciaríamos los Managers
        // y se los pasaríamos al RentalManager para operar.

            // 1. Creamos a los encargados (Managers)
            BookManager bookManager = new BookManager();
            UserManager userManager = new UserManager();
            RentalManager rentalManager = new RentalManager();

            // 2. Creamos los datos
            Book elQuijote = new Book("Don Quijote", "Cervantes", 5);
            User papalaya = new User("Prometheus", "ID-001", "prom@gmail.com");

            // 3. Registramos los datos en sus respectivos departamentos (SRP)
            bookManager.addBooks(elQuijote);
            userManager.addUsers(papalaya);

            // 4. Intentamos un préstamo exitoso
            System.out.println("--- Intento de préstamo 1 ---");
            rentalManager.rentalBook(elQuijote, papalaya, bookManager, userManager);
            System.out.println("Copias restantes del Quijote: " + elQuijote.copies);

            // 5. Intentamos un préstamo con un usuario que NO está registrado
            System.out.println("\n--- Intento de préstamo 2 ---");
            User intruso = new User("Intruso", "ID-666", "mal@gmail.com");
            rentalManager.rentalBook(elQuijote, intruso, bookManager, userManager);

            // 6. Devolvemos el libro
            System.out.println("\n--- Devolución ---");
            rentalManager.giveBookBack(elQuijote, papalaya, bookManager, userManager);
            System.out.println("Copias tras devolución: " + elQuijote.copies);


    }

    // EJERCICIO: Concepto Básico

    // EJEMPLO CORRECTO: Las responsabilidades están separadas.
    // Si cambia la lógica de facturación, la clase Cafe ni se entera.
    public static class Cafe {
        public void generarCafe(String nombre) {System.out.println("Cafe listo: " + nombre);}
    }

    public static class PrecioCafe {
        public void generarFacturaCafe(String nombre, double precio) {}
    }

    // EJEMPLO INCORRECTO: Esta clase "sabe" demasiado.
    // Hace café, cobra y hace malabares. Si falla la máquina de café,
    // podrías romper accidentalmente la lógica de los malabares.
    public static class Cafe2 {
        public void generarCafe(String nombre) {System.out.println("Cafe listo: " + nombre);}
        public void generarFacturaCafe(String nombre, double precio) {}
        public void hacerMalabares(String nombre, int repeticiones) {}
    }

    // DIFICULTAD EXTRA: Sistema de Biblioteca

    public static class Book {
        String title;
        String author;
        int copies;
        int ID;

        public Book(String title, String author, int copies) {
            this.title = title;
            this.author = author;
            this.copies = copies;
            this.ID = 0;
        }
    }

    public static class User {
        String name;
        String id;
        String email;

        public User(String name, String id, String email) {
            this.name = name;
            this.id = id;
            this.email = email;
        }
    }

    // 1. Clase que NO CUMPLE el SRP:
    // Es una "God Class" (Clase Dios). Si la biblioteca crece,
    // este archivo se volverá inmanejable y propenso a errores.
    public static class LibraryManager {
        List<Book> books = new ArrayList<>();
        List<User> users = new ArrayList<>();
        List<Book> rental = new ArrayList<>();

        public void addBooks(Book book) { books.add(book); }
        public void addUsers(User user) { users.add(user); }

        public void rentalBook(Book book) {
            if (books.contains(book)) {
                book.copies--;
                rental.add(book);
            } else {
                System.out.println("El libro no está disponible");
            }
        }

        public void giveBookBack(Book book) {
            if (rental.contains(book)) {
                book.copies++;
                rental.remove(book);
            } else {
                System.out.println("El libro no ha sido prestado");
            }
        }
    }

    // 2. REFACTORIZACIÓN (SÍ cumple SRP):
    // Hemos dividido el problema en piezas pequeñas y especializadas.

    // Solo gestiona el catálogo de libros
    public static class BookManager {
        List<Book> books = new ArrayList<>();
        public void addBooks(Book book) { books.add(book); }
        public boolean exists(Book book) { return books.contains(book); }
    }

    // Solo gestiona el registro de socios
    public static class UserManager {
        List<User> users = new ArrayList<>();
        public void addUsers(User user) { users.add(user); }
    }

    // Solo gestiona la lógica de préstamos.
    // Fíjate que para trabajar, necesita que le pasen los otros Managers como colaboradores.
    public static class RentalManager {
        List<Book> rental = new ArrayList<>();

        public void rentalBook (Book book, User user, BookManager bm, UserManager um) {
            // SRP en acción: RentalManager no mira su propia lista de libros,
            // le pregunta al experto (BookManager).
            if (bm.books.contains(book) && um.users.contains(user)) {
                book.copies--;
                rental.add(book);
                System.out.println("El libro ha sido prestado");
            } else {
                System.out.println("El id del libro o del usuario son incorrectos");
            }
        }

        public void giveBookBack(Book book, User user, BookManager bm, UserManager um) {
            if (bm.books.contains(book) && um.users.contains(user)) {
                book.copies++;
                rental.remove(book);
            } else {
                System.out.println("El id del libro o del usuario son incorrectos");
            }
        }
    }
}

