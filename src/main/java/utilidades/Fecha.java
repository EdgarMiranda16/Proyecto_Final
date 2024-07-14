package utilidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Fecha {

    public static LocalDateTime cast(String origen) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(origen, formato);
    }
}
