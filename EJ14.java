import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;


public class EJ14 {
    public static void main(String[] args) {
        /*
         * EJERCICIO:
         * Crea dos variables utilizando los objetos fecha (date, o semejante) de tu lenguaje:
         * - Una primera que represente la fecha (día, mes, año, hora, minuto, segundo) actual.
         * - Una segunda que represente tu fecha de nacimiento (te puedes inventar la hora).
         * Calcula cuántos años han transcurrido entre ambas fechas.
         *
         * DIFICULTAD EXTRA (opcional):
         * Utilizando la fecha de tu cumpleaños, formatéala y muestra su resultado de
         * 10 maneras diferentes. Por ejemplo:
         * - Día, mes y año.
         * - Hora, minuto y segundo.
         * - Día de año.
         * - Día de la semana.
         * - Nombre del mes.
         * (lo que se te ocurra...)
         */

        LocalDateTime fechaNacimiento = LocalDateTime.of(1999, 11, 19, 0, 0, 0);
        LocalDateTime fechaActual = LocalDateTime.now();
        Period diferencia = Period.between(fechaNacimiento.toLocalDate(), fechaActual.toLocalDate());
        Duration diferenciaS = Duration.between(fechaNacimiento, fechaActual);

        System.out.println(diferencia.getYears() + " años " + diferencia.getMonths() + " meses " + diferencia.getDays() + " días");

        //DIFICULTAD EXTRA

        System.out.println(fechaNacimiento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.printf(String.format("%d horas, %d minutos y %d segundos\n", diferenciaS.toHoursPart(), diferenciaS.toMinutesPart(), diferenciaS.toSecondsPart()));
        System.out.println(fechaNacimiento.format(DateTimeFormatter.ofPattern("DDD")));
        System.out.println(fechaNacimiento.format(DateTimeFormatter.ofPattern("EEEE")));
        System.out.println(fechaNacimiento.format(DateTimeFormatter.ofPattern("MMMM")));
        System.out.println(fechaNacimiento.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy")));
        System.out.println(fechaNacimiento);
        System.out.println(String.format(diferenciaS.toDays() + " días"));
        System.out.println(String.format((diferenciaS.toDays())/7 + " semanas"));

    }
}