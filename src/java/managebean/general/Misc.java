package managebean.general;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 *
 * @author Kevin Ivan Sanchez Valdin
 */
public class Misc {

    public static java.util.Date getDateTimeActualJava() {
        java.util.Date date = null;
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String timeStamp = formato.format(Calendar.getInstance().getTime());

            date = formato.parse(timeStamp);
        } catch (ParseException ex) {
            Logg.error("No se pudo obtener la fecha, Parseo incorrecto " + ex.getMessage());
        }
        return date;
    }

    public static java.sql.Date getDateActualSQL() {
        long miliseconds = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(miliseconds);
        return date;
    }

    public static Timestamp getDateTimeActualSQL() {
        long miliseconds = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(miliseconds);
        return timestamp;
    }

    public static java.sql.Date transformDateJavaSql(java.util.Date jDate) {
        long timeInMilliSeconds = jDate.getTime();
        java.sql.Date sqlDate = new java.sql.Date(timeInMilliSeconds);
        return sqlDate;
    }

    public static Timestamp transformDateTimeJavaSql(java.util.Date jDate) {
        long timeInMilliSeconds = jDate.getTime();
        Timestamp timestamp = new Timestamp(timeInMilliSeconds);
        return timestamp;
    }

    public static java.util.Date transformDateSqlJava(java.sql.Date sqlDate) {
        long timeInMilliSeconds = sqlDate.getTime();
        java.sql.Date jDate = new java.sql.Date(timeInMilliSeconds);
        return jDate;
    }

    public static Timestamp transformDateTimeSqlJava(java.sql.Date sqlDate) {
        long timeInMilliSeconds = sqlDate.getTime();
        Timestamp timestamp = new Timestamp(timeInMilliSeconds);
        return timestamp;
    }

    public static String getFormatDate(Object date) {
        String dateFormat = "";
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        if (date instanceof java.sql.Date) {
            dateFormat = formato.format((java.sql.Date) date);
        } else if (date instanceof java.util.Date) {
            dateFormat = formato.format((java.util.Date) date);
        }
        return dateFormat;
    }
    private static final String LETRAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String getFormatDate(Object date, String formato2) {
        String dateFormat = "";
        SimpleDateFormat formato = new SimpleDateFormat(formato2);
        if (date instanceof java.sql.Date) {
            dateFormat = formato.format((java.sql.Date) date);
        } else if (date instanceof java.util.Date) {
            dateFormat = formato.format((java.util.Date) date);
        }
        return dateFormat;
    }

    public static String generarFolio() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(LETRAS.length());
            sb.append(LETRAS.charAt(index));
        }

        sb.append("-");

        int numero = random.nextInt(10000);
        sb.append(String.format("%04d", numero));

        return sb.toString();
    }

    public static String obtenerValorParametro(String queryString, String nombreParametro) {
        if (queryString != null) {
            String[] paramsArray = queryString.split("&");
            for (String param : paramsArray) {
                if (param.startsWith(nombreParametro + "=")) {
                    return param.substring(nombreParametro.length() + 1);
                }
            }
        }
        return null;
    }
}
