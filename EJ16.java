import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EJ16 {
    public static void main(String[] args) {
        /*
         * EJERCICIO: Extraer números de un texto.
         */

        String texto = "PR0M3TH3U5 E5 UN S3M1D10S";

        // Usamos el cuantificador '+' para capturar números completos (uno o más dígitos).
        // Si usáramos solo [0-9], el número "10" se contaría como dos números separados.
        String regex = "[0-9]+";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(texto);

        int count = 0;
        // matcher.find() es el "motor" que va saltando de coincidencia en coincidencia.
        while (matcher.find()) {
            count++;
            // matcher.group() devuelve el texto exacto que encajó con la expresión regular.
            System.out.println("Número encontrado: " + matcher.group());
        }
        System.out.println("Se han encontrado: " + count + " números en el texto");

        // LLamada a la resolución del ejercicio extra
        dificultadExtra();
    }

    public static void dificultadExtra() {
        String texto = "linietofolgado@gmail.com, 06/02/2026, +34 2134234, https://www.google.com/";

        // 1. EMAIL: Añadimos '+' al final de [a-z] para que acepte extensiones largas (.com, .info).
        // Sin el '+', solo buscaría extensiones de una única letra.
        String regexEmail = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]+";

        // 2. FECHA: Usamos {n} para definir la longitud exacta.
        // Es más preciso que [0-9]+ porque evita validar fechas imposibles como 123/456/7890.
        String regexFecha = "[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}";

        // 3. TELÉFONO: Añadimos '\\s?' para que el espacio sea opcional.
        // El símbolo '+' se escapa con '\\+' porque en Regex significa "uno o más".
        String regexTelefono = "\\+[0-9]+\\s?[0-9]+";

        // 4. URL: Usamos '[\\S]+' para capturar tod0 lo que no sea un espacio.
        // Ojo: En textos con comas pegadas, \\S podría incluir la coma en el resultado.
        String regexUrl = "https://[\\S]+\\.[a-z]+";

        // Compilación de patrones (Se hace una vez para ganar eficiencia)
        Pattern patternEmail = Pattern.compile(regexEmail);
        Pattern patternFecha = Pattern.compile(regexFecha);
        Pattern patternTelefono = Pattern.compile(regexTelefono);
        Pattern patternUrl = Pattern.compile(regexUrl);

        // Vinculación de los patrones con el texto a analizar
        Matcher matcherEmail = patternEmail.matcher(texto);
        Matcher matcherFecha = patternFecha.matcher(texto);
        Matcher matcherTelefono = patternTelefono.matcher(texto);
        Matcher matcherUrl = patternUrl.matcher(texto);

        // Usamos .find() para localizar la primera coincidencia dentro del String largo.
        // Si quisiéramos validar que TOD0 el string es un email, usaríamos .matches().
        if (matcherEmail.find()) { System.out.println("Email encontrado: " + matcherEmail.group()); }
        if (matcherFecha.find()) { System.out.println("Fecha encontrada: " + matcherFecha.group()); }
        if (matcherTelefono.find()) { System.out.println("Teléfono encontrado: " + matcherTelefono.group()); }
        if (matcherUrl.find()) { System.out.println("URL encontrada: " + matcherUrl.group()); }
    }
}
