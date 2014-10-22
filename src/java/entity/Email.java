package entity;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

    private String from = "engenhariadesoftware2014@gmail.com";
    private String password = "engenhariasoftwarede2014";
    private String to;
    private String subject;
    private String content;
    private String host = "localhost";

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static boolean sendEmail(Email email) {
        try {
            // Get system properties
            Properties properties = System.getProperties();
            
            // Setup mail server
            properties.setProperty("mail.smtp.host", email.getHost());
            properties.setProperty("mail.user", email.getFrom());
            properties.setProperty("mail.password", email.getPassword());
            
            Session session = Session.getDefaultInstance(properties);
            
            MimeMessage message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(email.getFrom()));
            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(email.getTo()));
            
            message.setSubject(email.getSubject());
            // Now set the acctual message
            message.setText(email.getContent());
            // Send message
            Transport.send(message);
        } catch (MessagingException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}