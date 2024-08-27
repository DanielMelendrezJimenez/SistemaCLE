package managebean.general;

import java.io.IOException;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import utils.constantes.VariablesSistema;

/**
 *
 * @author Kevin Ivan Sanchez Valdin
 */
public class Email {

    private String to;
    private String subject;
    private String content;
    private String header;
    private String footer;

    public Email(String to, String subject, String content, String header, String footer) {
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.header = header;
        this.footer = footer;
    }
 public Email(String to, String subject, String content) {
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    public void send() throws AddressException, MessagingException, IOException {
        final String username = VariablesSistema.USERNAME_EMAIL;
        final String password = VariablesSistema.PASSWORD_EMAIL;
        Properties prop = new Properties();
        prop.put("mail.smtp.host", VariablesSistema.HOST_EMAIL);
        prop.put("mail.smtp.port", VariablesSistema.PORT_EMAIL);
        prop.put("mail.smtp.auth", VariablesSistema.AUTH_EMAIL);
        prop.put("mail.smtp.starttls.enable", VariablesSistema.STARTTLS_ENABLE);

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(to)
        );
        message.setSubject(subject);

        MimeMultipart multipart = new MimeMultipart("related");
        BodyPart messageBodyPart = new MimeBodyPart();

        StringBuilder html = new StringBuilder();

        html.append(content);
        messageBodyPart.setContent(html.toString(), "text/html; charset=utf-8");

        multipart.addBodyPart(messageBodyPart);

        MimeBodyPart imgPart = new MimeBodyPart();
        imgPart.attachFile(header);
        imgPart.setContentID("<header>");
        multipart.addBodyPart(imgPart);

        message.setContent(multipart);
        Transport.send(message);

    }
    public void sendSinEncabezados() throws AddressException, MessagingException, IOException {
      final String username = VariablesSistema.USERNAME_EMAIL;
        final String password = VariablesSistema.PASSWORD_EMAIL;
        Properties prop = new Properties();
        prop.put("mail.smtp.host", VariablesSistema.HOST_EMAIL);
        prop.put("mail.smtp.port", VariablesSistema.PORT_EMAIL);
        prop.put("mail.smtp.auth", VariablesSistema.AUTH_EMAIL);
        prop.put("mail.smtp.starttls.enable", VariablesSistema.STARTTLS_ENABLE);
        prop.put("mail.smtp.ssl.trust", "*");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(to)
        );
        message.setSubject(subject);

        MimeMultipart multipart = new MimeMultipart("related");
        BodyPart messageBodyPart = new MimeBodyPart();

        StringBuilder html = new StringBuilder();

        html.append(content);
        messageBodyPart.setContent(html.toString(), "text/html; charset=utf-8");

        multipart.addBodyPart(messageBodyPart);

       
      

        message.setContent(multipart);
        Transport.send(message);

    }
}
