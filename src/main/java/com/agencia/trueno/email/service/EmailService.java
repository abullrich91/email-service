package com.agencia.trueno.email.service;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.agencia.trueno.email.model.EmailFormRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final String username = "somostrueno@gmail.com";
    private final String organizationEmail = "pvarela@all-kom.com, sgagliardi@all-kom.com";
    private final String password = "altoruido";

    public void sendUserEmail(final String email, final Message message) {

        try {
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(email));
            message.setSubject("Confirmación de contacto");
            message.setText("Muchas gracias por contactarte con nosotros. " +
                "A la brevedad un representante de AllKom se comunicará con ud.");

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendEmail(final EmailFormRequest request, final Boolean isOrganizationRecipient) {
        Properties props = setGmailProperties();
        Session session = setSession(props);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));

            if (isOrganizationRecipient) {
                sendOrganizationEmail(request, message);
            } else {
                sendUserEmail(request.getEmail(), message);
            }
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendOrganizationEmail(final EmailFormRequest request, final Message message) {

        try {

            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(organizationEmail));
            message.setSubject("Confirmación de contacto");
            message.setText("Muchas gracias por contactarte con nosotros. " +
                "A la brevedad un representante de AllKom se comunicará con ud.");

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private Properties setGmailProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        return props;
    }

    private Session setSession(final Properties props) {
        return Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
    }
}
