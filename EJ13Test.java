import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EJ13Test {

    @Test
    void sumar() {
        //1. Config
        int a = 123;
        int b = 456;

        //2. Ejecucion
        int resultado = EJ13.sumar(a, b);

        //3. Comprobacion
        assertEquals(579, resultado, "La suma debería ser 579");

    }

    @Test
    void testCargarDiccionario() {
        Map<String, Object> dicc = EJ13.cargarDiccionario();

        assertTrue(dicc.containsKey("name"));
        assertTrue(dicc.containsKey("age"));
        assertTrue(dicc.containsKey("birth_date"));
        assertTrue(dicc.containsKey("programming_languages"));

    }

    @Test
    void testCargarDiccionario2() {
        Map<String, Object> dicc = EJ13.cargarDiccionario();

        assertEquals("Prometheus", dicc.get("name"));
        assertEquals(27, dicc.get("age"));
        assertEquals("Unknown", dicc.get("birth_date"));
        assertEquals(Arrays.asList("God", "Demigod", "Human"), dicc.get("programming_languages"));

    }
}