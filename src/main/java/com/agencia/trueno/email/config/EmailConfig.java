package com.agencia.trueno.email.config;

import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    private final String username = "somostrueno@gmail.com";
    // private final String organizationEmail = "pvarela@all-kom.com, sgagliardi@all-kom.com";
    private final String organizationEmail = "abullrich91@gmail.com, alebull@hotmail.com";
    private final String password = "altoruido";

    private Properties setGmailProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.user", username);
        props.put("mail.smtp.password", password);

        return props;
    }

    @Bean
    public Session session() {
            return Session.getInstance(setGmailProperties(),
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }
}
