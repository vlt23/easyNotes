package es.dad.easynotes;

import com.sun.mail.smtp.SMTPTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@RestController
public class EmailController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String username = "easynotes.dad.2020@gmail.com";
    private final String password = "easynotes.20";

    @PostMapping(value = "/email")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Boolean> sendEmail(@RequestBody Email email) {
        logger.info("Received: " + email.toString());

        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.socketFactory.port", "587");
        props.setProperty("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getUserMail(), false));
            message.setSubject("Test");  // TODO
            message.setText("Hello " + email.getUsername() + ". This is a test");  // TODO

            SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
            t.connect("smtp.gmail.com", username);
            t.sendMessage(message, message.getAllRecipients());
            t.close();
            logger.info("Successfully sent email");
        } catch (MessagingException e) {
            logger.error("Error while sending email", e);
        }

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
