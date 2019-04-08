package com.agencia.trueno.email.service;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.agencia.trueno.email.config.EmailConfig;
import com.agencia.trueno.email.model.EmailFormRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailConfig emailConfig;

    private final String username = "somostrueno@gmail.com";
    private final String organizationEmail = "pvarela@all-kom.com, sgagliardi@all-kom.com";
    // private final String organizationEmail = "abullrich91@gmail.com, alebull@hotmail.com";

    public void sendUserEmail(final EmailFormRequest request) {

        try {
            Message message = prepareEmail();
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(request.getEmail()));
            message.setSubject("Confirmación de contacto");
            message.setText("Muchas gracias por contactarte con nosotros. " +
                "A la brevedad un representante de AllKom se comunicará con ud.");

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private Message prepareEmail() {
        Session session = emailConfig.session();

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            return message;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendOrganizationEmail(final EmailFormRequest request) {

        try {
            Message message = prepareEmail();
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(organizationEmail));
            message.setSubject("Nuevo Usuario");
            message.setText("Un nuevo usuario se ha registrado." +
                "\n Nombre Completo: " + request.getFullName() +
                "\n Email: " + request.getEmail() +
                "\n Empresa: " + request.getOrganization() +
                "\n Teléfono: " + request.getPhone() +
                "\n Mensaje: " + request.getMessage());

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
