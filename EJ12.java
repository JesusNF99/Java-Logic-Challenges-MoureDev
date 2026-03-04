import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.google.gson.*;
import org.w3c.dom.*;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class EJ12 {
    public static void main(String[] args) {
        /*
         * EJERCICIO:
         * Desarrolla un programa capaz de crear un archivo XML y JSON que guarde los
         * siguientes datos (haciendo uso de la sintaxis correcta en cada caso):
         * - Nombre
         * - Edad
         * - Fecha de nacimiento
         * - Listado de lenguajes de programación
         * Muestra el contenido de los archivos.
         * Borra los archivos.
         *
         * DIFICULTAD EXTRA (opcional):
         * Utilizando la lógica de creación de los archivos anteriores, crea un
         * programa capaz de leer y transformar en una misma clase custom de tu
         * lenguaje los datos almacenados en el XML y el JSON.
         * Borra los archivos.
         */

        //EJERCICIO
        MenuPrincipal();

    }
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void MenuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        String navegacion;

        do{
            showMenu();
            navegacion = scanner.nextLine();

            switch (navegacion) {
                case "1": {
                    cargarUsuario();
                    break;
                }
                case "2": {
                    consultarUsuario();
                    break;
                }
                case "3": {
                    menuXML();
                    break;
                }
                case "4": {
                    salir();
                }
                default:
                    System.err.println("Selección no valida");
                    break;
            }


        } while (!navegacion.equals("4"));
    }

    public static void showMenu() {
        System.out.println("______________________________________________");
        System.out.println("\nMENÚ PRINCIPAL JSON");
        System.out.println("Escriba (1) - AÑADIR USUARIO\nEscriba (2) - CONSULTAR USUARIOS\nEscribe (3) - CAMBIAR A XML\nEscribe (4) - FINALIZAR PROGRAMA");
    }

    public static class Usuario {
        String nombre;
        int edad;
        String fechaNacimiento;
        String[] lenguajes;

        //Constructores:
        public Usuario() {

        }
        public Usuario(String nombre, int edad, String fechaNacimiento, String[] lenguajes) {
            this.nombre = nombre;
            this.edad = edad;
            this.fechaNacimiento = fechaNacimiento;
            this.lenguajes = lenguajes;


        }

    }


    public static void cargarUsuario() {
        List<Usuario> usuarios = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        File archivo = new File("usuarios.json");
        System.out.println("Escribe el nombre del usuario");
        String nombre = scanner.nextLine();
        System.out.println("Escribe la edad del usuario");
        int edad = scanner.nextInt();
        System.out.println("Escribe la fecha de nacimiento del usuario");
        scanner.nextLine();
        String fechaNacimiento = scanner.nextLine();
        System.out.println("Escribe los lenguajes de programación del usuario");
        String[] lenguajes = scanner.nextLine().split(",");
        usuarios.add(new Usuario(nombre, edad, fechaNacimiento, lenguajes));
        try {
            Writer writer = new FileWriter("usuarios.json", true);
            gson.toJson (usuarios, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Usuario añadido con éxito");
    }

    public static void consultarUsuario() {
        File archivo = new File("usuarios.json");
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
            System.out.println("Usuarios consultados con éxito");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void salir() {
        File archivo = new File("usuarios.json");
        try {
            Files.deleteIfExists(Path.of("usuarios.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.exit(0);
    }

    //XML

    public static void menuXML() {
        Scanner scanner = new Scanner(System.in);
        String navegacion;

        do{
            showMenuXML();
            navegacion = scanner.nextLine();

            switch (navegacion) {
                case "1": {
                    cargarUsuarioXML();
                    break;
                }
                case "2": {
                    consultarUsuarioXML();
                    break;
                }
                case "3": {
                    MenuPrincipal();
                    break;
                }
                case "4": {
                    salirXML();
                }
                default:
                    System.err.println("Selección no valida");
                    break;
            }
        } while (!navegacion.equals("4"));
    }

    public static void showMenuXML() {
        System.out.println("______________________________________________");
        System.out.println("\nMENÚ PRINCIPAL XML");
        System.out.println("Escriba (1) - AÑADIR USUARIO\nEscriba (2) - CONSULTAR USUARIOS\nEscribe (3) - CAMBIAR A JSON\nEscribe (4) - FINALIZAR PROGRAMA");
    }

    public static void cargarUsuarioXML() {
        List<Usuario> usuarios = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        //Creamos el array con el input del usuario
        System.out.println("Escribe el nombre del usuario");
        String nombre = scanner.nextLine();
        System.out.println("Escribe la edad del usuario");
        int edad = scanner.nextInt();
        System.out.println("Escribe la fecha de nacimiento del usuario");
        scanner.nextLine();
        String fechaNacimiento = scanner.nextLine();
        System.out.println("Escribe los lenguajes de programación del usuario");
        String[] lenguajes = scanner.nextLine().split(",");
        usuarios.add(new Usuario(nombre, edad, fechaNacimiento, lenguajes));

        try {
            //Paso 1: Crear documento XML vacío
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            //Paso 2: Crear el nodo raíz
            Element root = document.createElement("usuarios");
            document.appendChild(root);

            //Paso 3: Recorrer la lista de usuarios
            for (Usuario u : usuarios) {
                //Paso 3.1: Crear usuario
                Element usuarioE1 = document.createElement("usuario");
                //Paso 3.2: Crear nombre
                Element nombreE1 = document.createElement("nombre");
                nombreE1.setTextContent(u.nombre);
                usuarioE1.appendChild(nombreE1);

                //Paso 3.3: Crear edad
                Element edadE1 = document.createElement("edad");
                edadE1.setTextContent(String.valueOf(u.edad));
                usuarioE1.appendChild(edadE1);

                //Paso 3.4: Crear fecha de nacimiento
                Element fechaE1 = document.createElement("fechaNacimiento");
                fechaE1.setTextContent(u.fechaNacimiento);
                usuarioE1.appendChild(fechaE1);

                //Paso 3.5: Crear lenguajes
                Element lenguajesE1 = document.createElement("lenguajes");
                for (String l : u.lenguajes) {
                    Element lenguajeE1 = document.createElement("lenguaje");
                    lenguajeE1.setTextContent(l);
                    lenguajesE1.appendChild(lenguajeE1);
                }
                usuarioE1.appendChild(lenguajesE1);

                //Paso 3.6: Añadir usuario al nodo raíz
                root.appendChild(usuarioE1);
            }

            //Paso 4: Escribir el documento XML en un archivo
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("usuarios.xml"));


            transformer.transform(source, result);

            System.out.println("Usuario añadido con éxito");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void salirXML() {
        File archivo = new File("usuarios.xml");
        try {
            Files.deleteIfExists(Path.of("usuarios.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.exit(0);
    }

    public static void consultarUsuarioXML() {
        File archivo = new File("usuarios.xml");

        if (!archivo.exists()) {
            System.out.println("No hay usuarios en XML");
            return;
        }

        try {
            // PASO 1: Cargar el XML existente
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(archivo);
            document.getDocumentElement().normalize();

            // PASO 2: Obtener lista de <usuario>
            NodeList listaUsuarios = document.getElementsByTagName("usuario");

            // PASO 3: Recorrer usuarios
            for (int i = 0; i < listaUsuarios.getLength(); i++) {
                Node nodo = listaUsuarios.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element usuario = (Element) nodo;

                    String nombre = usuario.getElementsByTagName("nombre").item(0).getTextContent();
                    String edad = usuario.getElementsByTagName("edad").item(0).getTextContent();
                    String fecha = usuario.getElementsByTagName("fechaNacimiento").item(0).getTextContent();

                    System.out.println("----- USUARIO -----");
                    System.out.println("Nombre: " + nombre);
                    System.out.println("Edad: " + edad);
                    System.out.println("Fecha nacimiento: " + fecha);

                    // Lenguajes
                    NodeList lenguajes = usuario
                            .getElementsByTagName("lenguajes")
                            .item(0)
                            .getChildNodes();

                    System.out.print("Lenguajes: ");
                    for (int j = 0; j < lenguajes.getLength(); j++) {
                        if (lenguajes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            System.out.print(lenguajes.item(j).getTextContent() + " ");
                        }
                    }
                    System.out.println("\n");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
