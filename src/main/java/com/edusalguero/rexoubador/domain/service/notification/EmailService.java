package com.edusalguero.rexoubador.domain.service.notification;


public interface EmailService {

    void send(String to, String subject, String body);
}