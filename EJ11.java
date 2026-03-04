import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class EJ11 {
    public static void main(String[] args) {
        /*
         * IMPORTANTE: Sólo debes subir el fichero de código como parte del ejercicio.
         *
         * EJERCICIO:
         * Desarrolla un programa capaz de crear un archivo que se llame como
         * tu usuario de GitHub y tenga la extensión .txt.
         * Añade varias líneas en ese fichero:
         * - Tu nombre.
         * - Edad.
         * - Lenguaje de programación favorito.
         * Imprime el contenido.
         * Borra el fichero.
         *
         * DIFICULTAD EXTRA (opcional):
         * Desarrolla un programa de gestión de ventas que almacena sus datos en un
         * archivo .txt.
         * - Cada producto se guarda en una línea del archivo de la siguiente manera:
         *   [nombre_producto], [cantidad_vendida], [precio].
         * - Siguiendo ese formato, y mediante terminal, debe permitir añadir, consultar,
         *   actualizar, eliminar productos y salir.
         * - También debe poseer opciones para calcular la venta total y por producto.
         * - La opción salir borra el .txt.
         */

        try {
            FileWriter fw = new FileWriter("output.txt");
            fw.write("Prometeo alcanza el sol");
            fw.write("\nY John también");
            fw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            BufferedReader fr = new BufferedReader(new FileReader("output.txt"));
            String line;
            while ((line = fr.readLine()) != null) {
                System.out.println(line);
            }
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MenuPrincipal();
    }

    //EJERCICIO EXTRA

    public static void MenuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        String navegacion;


      do {
          showMenu();
          navegacion = scanner.nextLine();

        switch (navegacion) {
            case "1": {
                anadirproducto();
                break;
            }
            case "2": {
                consultarproducto();
                break;
            }
            case "3": {
                actualizarproducto();
                break;
            }
            case "4": {
                eliminarproducto();
                break;
            }
            case "5": {
                salir();
                break;
            }
            default:
                System.err.println("Selección no valida");
                break;

        }

      } while (!navegacion.equals("5"));

    }

    public static void showMenu() {
        System.out.println("____________________________________________________");
        System.out.println("\nMENÚ PRINCIPAL");
        System.out.println("Escriba (1) - AÑADIR PRODUCTOS\nEscriba (2) - CONSULTAR PRODUCTOS\nEscribe (3) - ACTUALIZAR PRODUCTOS\nEscribe (4) - ELIMINAR PRODUCTOS\nEscribe (5) - FINALIZAR PROGRAMA");
    }

    public static void anadirproducto() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escribe el nombre del producto");
        String nombre = scanner.nextLine();
        System.out.println("Escribe la cantidad del producto");
        String cantidad = scanner.nextLine();
        System.out.println("Escribe el precio del producto");
        String precio = scanner.nextLine();

        File productos = new File("productos.txt");

        try {
            // Use append mode (true) to add to the file instead of overwrite
            FileWriter fw = new FileWriter(productos, true);
            // Format: [nombre], [cantidad], [precio]
            fw.write("[" + nombre + "], [" + cantidad + "], [" + precio + "]\n");
            fw.close();
            System.out.println("Producto añadido con éxito \n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void consultarproducto() {
        File productos = new File("productos.txt");

        try {
            BufferedReader br = new BufferedReader(new FileReader(productos));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
            System.out.println("Productos consultados con éxito \n");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void actualizarproducto() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre del producto a actualizar:");
        String nombre = sc.nextLine();
        File productos = new File("productos.txt");
        ArrayList<String> line = new ArrayList<>();
        boolean encontrado = false;

        try {
            BufferedReader br = new BufferedReader(new FileReader(productos));
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains("[" + nombre + "]")) {
                    encontrado = true;
                    System.out.println("Escribe la nueva cantidad del producto");
                    String cantidad = sc.nextLine();
                    System.out.println("Escribe el nuevo precio del producto");
                    String precio = sc.nextLine();
                    line.add("[" + nombre + "], [" + cantidad + "], [" + precio + "]");
                } else {
                    line.add(linea);
                }
            }
            br.close();
            
        } catch (Exception e) {
            throw new RuntimeException(e);
            
        }
        if (!encontrado) {
            System.out.println("Producto no encontrado");
            return;
        }

        try {
            FileWriter fw = new FileWriter(productos);
            for (String l : line) {
                fw.write(l + "\n");
            }
            fw.close();
            System.out.println("Producto actualizado con éxito \n");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public static void eliminarproducto() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre del producto a eliminar:");
        String nombre = sc.nextLine();
        File productos = new File("productos.txt");
        ArrayList<String> line = new ArrayList<>();
        boolean encontrado = false;

        try {
            BufferedReader br = new BufferedReader(new FileReader(productos));
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains("[" + nombre + "]")) {
                    encontrado = true;
                } else {
                    line.add(linea);
                }
            }
            br.close();

        } catch (Exception e) {
            throw new RuntimeException(e);

        }
        if (!encontrado) {
            System.out.println("Producto no encontrado");
            return;
        }

        try {
            FileWriter fw = new FileWriter(productos);
            for (String l : line) {
                fw.write(l + "\n");
            }
            fw.close();
            System.out.println("Producto actualizado con éxito \n");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public static void salir() {
        try {
            Files.deleteIfExists(Path.of("productos.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

}
