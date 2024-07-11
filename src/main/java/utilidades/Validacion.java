package utilidades;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validacion {

    public static boolean validarEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public static boolean validarVacios(String... campos) {
        return Arrays.stream(campos)
                .allMatch(campo -> !campo.strip().isEmpty());
    }

    public static <T> boolean validarNulos(T... campos) {
        return Arrays.stream(campos)
                .allMatch(campo -> campo != null);
    }

    public static boolean validarCeros(Number... campos) {
        return Arrays.stream(campos)
                .allMatch(campo -> campo.doubleValue() != 0);
    }

    public static void validarNumeros(KeyEvent e) {
        int key = e.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            e.consume();
        }
    }

    public static void validarDecimales(KeyEvent e) {
        char key = e.getKeyChar();

        if (Character.isLetter(key)) {
            e.consume();
            return;
        }

        try {
            Double.parseDouble(((JTextField) e.getSource()).getText() + key);
        } catch (NumberFormatException error) {
            e.consume();
        }

    }

    public static void validarLongitud(KeyEvent e, String texto, int longitud) {
        if (texto.trim().length() == longitud) {
            e.consume();
        }
    }

    public static void validarValorMaximo(KeyEvent e, String texto, int valorMaximo) {
        if (texto.trim().length() == 0) {
            return;
        }

        if (Integer.parseInt(texto) > valorMaximo) {
            e.consume();
        }
    }

    public static boolean validarValorMinimo(String texto, int valorMinimo) {
        return texto.trim().length() < valorMinimo;
    }

    public static int confirmacion() {
        Object[] options = {"Sí", "No"};
        return JOptionPane.showOptionDialog(null, "¿Está seguro de realizar esta acción?", "Confirmación",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }

    public static boolean fechaNoMayorActual(LocalDate fecha) {
        LocalDate fechaActual = LocalDate.now();
        return fecha.isAfter(fechaActual);
    }

    public static boolean sonHorasDiferentes(LocalTime hora1, LocalTime hora2) {
        return hora1.equals(hora2);
    }

    public static boolean horaNoMenor(LocalTime horaInicio, LocalTime horaFin) {
        return !horaInicio.isBefore(horaFin);
    }

    public static boolean diferenciaMaximaUnaHora(LocalTime horaInicio, LocalTime horaFin) {
        long minutosDiferencia = Duration.between(horaInicio, horaFin).toMinutes();
        return minutosDiferencia > 60;
    }
}
