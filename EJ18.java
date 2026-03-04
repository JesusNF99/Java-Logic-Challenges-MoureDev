import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class EJ18 {
    public static void main(String[] args){

        /*
         * EJERCICIO: Operaciones con ArrayList (Estructura que soporta índices y orden).
         */

        ArrayList<String> Datos = new ArrayList<>();

        // Añadir elemento (por defecto al final)
        Datos.add("Demigod");

        // Métodos de SequencedCollection (Java 21+) para añadir en extremos
        Datos.addFirst("Prometheus");
        Datos.addLast("John");
        System.out.println(Datos);

        // Actualizar valor en posición concreta (0 es el primer índice)
        Datos.set(0, "Jack");
        System.out.println(Datos);

        // Añadir bloque al final (addAll sin índice)
        Datos.addAll(List.of("Faye", "Zeus"));
        System.out.println(Datos);

        // Añadir bloque en posición específica (desplaza los elementos existentes)
        Datos.addAll(4, List.of("Pyre", "Hades"));

        // Eliminar el primer elemento
        Datos.removeFirst();
        System.out.println(Datos);

        // Comprobar existencia (devuelve boolean)
        System.out.println(Datos.contains("Faye"));

        // Vaciar la lista por completo
        Datos.clear();
        System.out.println(Datos);

        /*
         * DIFICULTAD EXTRA: Operaciones de Conjuntos (Set).
         * Usamos HashSet porque no permite duplicados y optimiza estas operaciones.
         */

        HashSet<String> setDatos = new HashSet<>();
        setDatos.addAll(List.of("A", "B", "C", "D", "E"));
        System.out.println(setDatos);

        HashSet<String> setDatos2 = new HashSet<>(List.of("D", "E", "F", "G"));
        System.out.println(setDatos2);

        // --- UNIÓN ---
        // Creamos una COPIA (new HashSet<>) para no modificar el conjunto original (setDatos).
        HashSet<String> union = new HashSet<>(setDatos);
        union.addAll(setDatos2); // Junta elementos de ambos, eliminando duplicados automáticamente.
        System.out.println("Union " + union);

        // --- INTERSECCIÓN ---
        HashSet<String> interseccion = new HashSet<>(setDatos);
        interseccion.retainAll(setDatos2); // Mantiene solo los elementos presentes en AMBOS conjuntos.
        System.out.println("Intersección " + interseccion);

        // --- DIFERENCIA (A - B) ---
        HashSet<String> diferencia = new HashSet<>(setDatos);
        diferencia.removeAll(setDatos2); // Quita de A todos los elementos que también estén en B.
        System.out.println("Diferencia " + diferencia);

        // --- DIFERENCIA SIMÉTRICA (A Δ B) ---
        // Lógica: (A ∪ B) - (A ∩ B). Elementos que están en uno o en otro, pero no en ambos.
        // Reutilizamos la variable 'union' que ya contiene todos los elementos de ambos.
        union.removeAll(interseccion);
        System.out.println("Diferencia simétrica " + union);
    }
}
