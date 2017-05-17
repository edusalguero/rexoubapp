package com.edusalguero.rexoubador.infraestructure.service.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SmtpEmailService implements com.edusalguero.rexoubador.domain.service.notification.EmailService {
    private static final Logger logger = LoggerFactory.getLogger(SmtpEmailService.class);

    private final JavaMailSender emailSender;
    private final String from;

    @Autowired
    public SmtpEmailService(JavaMailSender emailSender, @Value("${rexoubador.email.from}") String from) {
        this.emailSender = emailSender;
        this.from = from;
    }

    @Override
    public void send(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom(from);
        logger.info("Sending email message: " + message.toString());
        emailSender.send(message);
        logger.info("Email message sent");
    }
}
