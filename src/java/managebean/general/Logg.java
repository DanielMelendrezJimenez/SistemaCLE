package managebean.general;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Kevin Ivan Sanchez Valdin
 */
public class Logg {

    public static String loggReset = "\033[30m";
    public static String loggGris = "\033[1m";
    public static String loggNegro = "\033[2m";
    public static String loggRojo = "\033[31m";
    public static String loggVerdeLimon = "\033[32m";
    public static String loggAmarilloLima = "\033[33m";
    public static String loggAzulRey = "\033[34m";
    public static String loggRosa = "\033[35m";
    public static String loggAzulTurquesa = "\033[36m";
    public static String loggGrisBajo = "\033[37m";
    public static String loggNegro2 = "\033[38m";
    public static String loggSubrayadoAzulRey = "\033[44m";
    public static String loggSubrayadoRosa = "\033[45m";
    public static String loggSubrayadoAmarillo = "\033[43m";
    public static String loggSubrayadoVerde = "\033[42m";
    public static String loggSubrayadoRojo = "\033[41m";
    public static String loggSubrayadoAzulTurquesa = "\033[46m";
    public static String loggSubrayadoGris = "\033[47m";

    public static void exito(String message) {
        DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        //Logg.loggVerdeLimon + "EXITO [" + dtf3.format(LocalDateTime.now()) + "]: " + message + Logg.loggReset);
         System.out.println("EXITO [" + dtf3.format(LocalDateTime.now()) + "]: " + message);

    }

    public static void debug(String message) {
        DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
       System.out.println("DEBUG [" + dtf3.format(LocalDateTime.now()) + "]: " + message);

    }

    public static void info(String message) {
        DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        //Logg.loggAzulRey + "INFO [" + dtf3.format(LocalDateTime.now()) + "]: " + message + Logg.loggReset);
         System.out.println("INFO [" + dtf3.format(LocalDateTime.now()) + "]: " + message);

    }

    public static void advertencia(String message) {
        DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        //Logg.loggRosa + "ADVERTENCIA [" + dtf3.format(LocalDateTime.now()) + "]: " + message + Logg.loggReset);
        System.out.println("ADVERTENCIA [" + dtf3.format(LocalDateTime.now()) + "]: " + message);
    }

    public static void error(String message) {
        DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    }


}
