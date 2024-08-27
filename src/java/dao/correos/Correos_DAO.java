/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.correos;

import java.io.IOException;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import managedbean.alumnos.Alumnos_MB;
import managebean.general.Email;
import managedbean.calificaciones.Calificaciones_consultar_MB;
import managedbean.grupos.Grupos_Consultar_MB;
import managedbean.grupos.Grupos_MB;
import utils.constantes.VariablesSistema;

/**
 *
 * @author Kevin Ivan Sanchez Valdin
 */
public class Correos_DAO {

    public static void enviarCorreoCalificaciones(Calificaciones_consultar_MB calificaciones, String grupo, String header, String footer) throws AddressException, IOException {
        System.out.println("ENTRA SERVLET CORREOS");

        String to = calificaciones.getCorreo();
        System.out.println(to);
        String asunto = "CALIFICACIONES " + grupo;

        String nombres = calificaciones.getAlumno();

        StringBuilder content = new StringBuilder();
        content.append("<table align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">");
        content.append("<tr class=\"header\" height=\"120px\">");
        content.append("<td colspan=\"12\">");
        content.append("<div class=\"container\" align=\"center\">");
        content.append("<img src=\"cid:header\" width=\"800\" class=\"img-responsive\" alt=\"Responsive image\">");
        content.append("</div>");
        content.append("</td>");
        content.append("</tr>");
        content.append("<tr >");
        content.append("<td colspan=\"12\" style=\"padding: 30px 60px\">");
        content.append("<table align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">");
        content.append("<tr>");
        content.append("<td>Hola estimado(a) <b>").append(nombres).append(grupo).append(".</td>");
        content.append("</tr>");
        content.append("<tr>");
        content.append("<td style=\"padding: 10px;\">");
        content.append("<table class=\"table\" align=\"center\" width=\"100%\" cellpadding=\"4\" role=\"presentation\">");

        content.append("<tr>");
        content.append("<td style=\"background-color: #fff !important;\"><b>Listening: </b></td>");
        content.append("<td>").append(calificaciones.getListening()).append("</td>");
        content.append("</tr>");

        content.append("<tr>");
        content.append("<td style=\"background-color: #fff !important;\"><b>Reading: </b></td>");
        content.append("<td>").append(calificaciones.getReading()).append("</td>");
        content.append("</tr>");

        content.append("<tr>");
        content.append("<td style=\"background-color: #fff !important;\"><b>Writing: </b></td>");
        content.append("<td>").append(calificaciones.getWriting()).append("</td>");
        content.append("</tr>");

        content.append("<tr>");
        content.append("<td style=\"background-color: #fff !important;\"><b>Speaking: </b></td>");
        content.append("<td>").append(calificaciones.getSpeaking()).append("</td>");
        content.append("</tr>");

        content.append("<tr>");
        content.append("<td style=\"background-color: #fff !important;\"><b>Promedio: </b></td>");
        content.append("<td>").append(calificaciones.getPromedio()).append("</td>");
        content.append("</tr>");

        content.append("</table>");
        content.append("</td>");
        content.append("</tr>");
        content.append("<tr>");
        content.append("<td style=\"padding-top: 30px; padding-bottom: 20px\">");
        content.append("<strong>Nota:</strong> La fecha y hora de la operación podrá variar respecto a la fecha/hora del sistema o configuración de sus servicios de correo o computadora en cuanto a su <b>zona horaria</b>");
        content.append("</td>");
        content.append("</tr>");
        content.append("</table>");
        content.append("</td>");
        content.append("</tr>");
        content.append("<tr >");
        content.append("<td colspan=\"12\" style=\"padding-left: 60px\">");
        content.append("A quien corresponda: La presente cuenta de correo electrónico <b>no es administrada por personal del Instituto Tecnológico de Toluca.</b> Si tiene alguna duda, deberá comunicarse directamente con la Institución o con el departamento al que compete su situación.<br>Teléfono: (52)(722)2087200<br>Página del Instituto Tecnológico de Toluca: <a href=\"http://www.ittoluca.edu.mx\">http://www.ittoluca.edu.mx</a>\n");
        content.append("</td>");
        content.append("</tr>");
        content.append("</table>");

        Email email = new Email(to, asunto, content.toString());
        try {
            email.sendSinEncabezados();
        } catch (MessagingException e) {
            System.out.println(e);
        }
    }

//    public static void enviarCorreoPago(Clientes_MB cliente, String header, String footer,
//            String to, String asunto, String mes, String vigencia, String nombres, String capturadoEl, String conceptoCorreo, double monto
//    ) throws AddressException, IOException {
//
//        StringBuilder content = new StringBuilder();
//
//        content.append("<table align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">");
//        content.append("<tr class=\"header\" height=\"120px\">");
//        content.append("<td colspan=\"12\">");
//        content.append("<div class=\"container\" align=\"center\">");
//        content.append("<img src=\"cid:header\" width=\"800\" class=\"img-responsive\" alt=\"Responsive image\">");
//        content.append("</div>");
//        content.append("</td>");
//        content.append("</tr>");
//        content.append("<tr >");
//        content.append("<td colspan=\"12\" style=\"padding: 30px 60px\">");
//        content.append("<table align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">");
//        content.append("<tr>");
//        content.append("<td>Hola estimado(a) <b>").append(nombres).append("</b>,<br><br>Se ha realizado la siguiente operación en la alberca:</td>");
//        content.append("</tr>");
//        content.append("<tr>");
//        content.append("<td style=\"padding: 10px;\">");
//        content.append("<table class=\"table\" align=\"center\" width=\"100%\" cellpadding=\"4\" role=\"presentation\">");
//        content.append("<tr>");
//        content.append("<td style=\"background-color: #fff !important;\"><b>Concepto: </b></td>");
//        content.append("<td>").append(conceptoCorreo).append("</td>");
//        content.append("</tr>");
//        content.append("<tr>");
//        content.append("<td style=\"background-color: #fff !important;\"><b>Monto: </b></td>");
//        content.append("<td>$").append(monto).append(" MXN</td>");
//        content.append("</tr>");
//        content.append("<tr>");
//        content.append("<td style=\"background-color: #fff !important;\"><b>Mes de cobertura: </b></td>");
//        content.append("<td>").append(mes).append("</td>");
//        content.append("</tr>");
//        content.append("<tr>");
//        content.append("<td style=\"background-color: #fff !important;\"><b>Fecha y hora de la operación: </b></td>");
//        content.append("<td>").append(capturadoEl).append("</td>");
//        content.append("</tr>");
//        content.append("<tr>");
//        content.append("<td style=\"background-color: #fff !important;\"><b>Vigente hasta: </b></td>");
//        content.append("<td>").append(vigencia).append("</td>");
//        content.append("</tr>");
//        content.append("</table>");
//        content.append("</td>");
//        content.append("</tr>");
//        content.append("<tr>");
//        content.append("<td style=\"padding-top: 30px; padding-bottom: 20px\">");
//        content.append("<strong>Nota:</strong> La fecha y hora de la operación podrá variar respecto a la fecha/hora del sistema o configuración de sus servicios de correo o computadora en cuanto a su <b>zona horaria</b>");
//        content.append("</td>");
//        content.append("</tr>");
//        content.append("</table>");
//        content.append("</td>");
//        content.append("</tr>");
//        content.append("<tr >");
//        content.append("<td colspan=\"12\" style=\"padding-left: 60px\">");
//        content.append("A quien corresponda: La presente cuenta de correo electrónico <b>no es administrada por personal del Instituto Tecnológico de Toluca.</b> Si tiene alguna duda, deberá comunicarse directamente con la Institución o con el departamento al que compete su situación.<br>Teléfono: (52)(722)2087200<br>Página del Instituto Tecnológico de Toluca: <a href=\"http://www.ittoluca.edu.mx\">http://www.ittoluca.edu.mx</a>\n");
//        content.append("</td>");
//        content.append("</tr>");
//        content.append("</table>");
//
//        Email email = new Email(to, asunto, content.toString(), header, footer);
//        try {
//            email.send();
//        } catch (MessagingException e) {
//
//        }
//    }
//
//    public static void enviarCorreoPagoRetrasado(Clientes_MB cliente, String header, String footer,
//            String folio, String inpHorario) throws AddressException, IOException {
//
//    }
//
//    public static void enviarCorreoPagoPorExpirar(Clientes_MB cliente, String header, String footer,
//            String folio, String inpHorario) throws AddressException, IOException {
//
//    }
//
//    public static void enviarCorreoDatosCuenta(Clientes_MB cliente, String header, String footer,
//            String to, String nombres, String nombreUsuario, String contra, String asuntoAcceso) throws AddressException, IOException {
//        StringBuilder contentAccess = new StringBuilder();
//        contentAccess.append("<table align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">");
//        contentAccess.append("<tr class=\"header\" height=\"120px\">");
//        contentAccess.append("<td colspan=\"12\" style=\"padding-left: 80px\">");
//        contentAccess.append("<div class=\"container\" align=\"center\">");
//        contentAccess.append("<img src=\"cid:header\" width=\"800\" class=\"img-responsive\" alt=\"Responsive image\">");
//        contentAccess.append("</div>");
//        contentAccess.append("</td>");
//        contentAccess.append("</tr>");
//        contentAccess.append("<tr >");
//        contentAccess.append("<td colspan=\"12\" style=\"padding: 30px 60px\">");
//        contentAccess.append("<table align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">");
//        contentAccess.append("<tr>");
//        contentAccess.append("<td>Hola estimado(a) <b>").append(nombres).append("</b>,<br><br>A continuación le presentamos los datos de acceso de su cuenta. Ingrese el siguiente enlace en un navegador: ").append(VariablesSistema.APP_URL).append("</td>");
//        contentAccess.append("</tr>");
//        contentAccess.append("<tr>");
//        contentAccess.append("<td style=\"padding: 10px;\">");
//        contentAccess.append("<table class=\"table\" align=\"center\" width=\"100%\" cellpadding=\"4\" role=\"presentation\">");
//        contentAccess.append("<tr>");
//        contentAccess.append("<td style=\"background-color: #fff !important;\"><b>Usuario: </b></td>");
//        contentAccess.append("<td>").append(nombreUsuario).append("</td>");
//        contentAccess.append("</tr>");
//        contentAccess.append("<tr>");
//        contentAccess.append("<td style=\"background-color: #fff !important;\"><b>Contraseña: </b></td>");
//        contentAccess.append("<td>").append(contra).append("</td>");
//        contentAccess.append("</tr>");
//        contentAccess.append("</table>");
//        contentAccess.append("</td>");
//        contentAccess.append("</tr>");
//        contentAccess.append("<tr>");
//        contentAccess.append("<td style=\"padding-top: 30px; padding-bottom: 20px\">");
//        contentAccess.append("<strong>Nota:</strong> La contraseña de acceso es la que se estableció al inscribirse en la alberca. Si la contraseña asignada automáticamente por el sistema no es de su agrado, puede cambiarla desde el portal en el menú <b>Cambiar contraseña</b> (8 caracteres mínimo).");
//        contentAccess.append("</td>");
//        contentAccess.append("</tr>");
//        contentAccess.append("</table>");
//        contentAccess.append("</td>");
//        contentAccess.append("</tr>");
//        contentAccess.append("<tr >");
//        contentAccess.append("<td colspan=\"12\" style=\"padding-left: 60px\">");
//        contentAccess.append("A quien corresponda: La presente cuenta de correo electrónico <b>no es administrada por personal del Instituto Tecnológico de Toluca.</b> Si tiene alguna duda, deberá comunicarse directamente con la institución o con el departamento al que compete su situación.<br>Teléfono: (52)(722)2087200<br>Página del Instituto Tecnológico de Toluca: <a href=\"http://www.ittoluca.edu.mx\">http://www.ittoluca.edu.mx</a>");
//        contentAccess.append("</td>");
//        contentAccess.append("</tr>");
//        contentAccess.append("<tr></tr>");
//        contentAccess.append("</table>");
//
//        Email emailDatos = new Email(to, asuntoAcceso, contentAccess.toString(), header, footer);
//        try {
//            emailDatos.send();
//        } catch (MessagingException e) {
//
//        }
//    }
//
//    public static void enviarCorreoCierreSemestre( String header, String footer,
//            String to, String nombres, String mensaje, String asunto) throws AddressException, IOException {
//        StringBuilder emailContent = new StringBuilder();
//
//        emailContent.append("<table align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">");
//        emailContent.append("<tr height=\"120px\">");
//        emailContent.append("<td colspan=\"12\">");
//        emailContent.append("<div align=\"center\">");
//        emailContent.append("<img src=\"cid:header\" width=\"800\" alt=\"Responsive image\">");
//        emailContent.append("</div>");
//        emailContent.append("</td>");
//        emailContent.append("</tr>");
//        emailContent.append("<tr>");
//        emailContent.append("<td colspan=\"12\" style=\"padding: 30px 60px\">");
//        emailContent.append("<p style=\"text-align: center;\">Hola estimado(a) <b>").append(nombres).append("</b>,</p>");
//        emailContent.append("<p>Le notificamos que se completó el proceso de cierre de semestre:</p>");
//        emailContent.append("<p style=\"padding: 10px;\">").append(mensaje).append("</p>");
//        emailContent.append("</td>");
//        emailContent.append("</tr>");
//        emailContent.append("<tr>");
//        emailContent.append("<td colspan=\"12\" style=\"padding-left: 60px;\">");
//        emailContent.append("<p>A quien corresponda: La presente cuenta de correo electrónico <b>no es administrada por personal del Instituto Tecnológico de Toluca.</b> Si tiene alguna duda, deberá comunicarse directamente con la Institución o con el departamento al que compete su situación.</p>");
//        emailContent.append("<p>Teléfono: (52)(722)2087200</p>");
//        emailContent.append("<p>Página del Instituto Tecnológico de Toluca: <a href=\"http://www.ittoluca.edu.mx\">http://www.ittoluca.edu.mx</a></p>");
//        emailContent.append("</td>");
//        emailContent.append("</tr>");
//        emailContent.append("</table>");
//        Email email = new Email(to, asunto, emailContent.toString(), header, footer);
//        try {
//            email.send();
//        } catch (MessagingException e) {
//
//        }
//    }
}
