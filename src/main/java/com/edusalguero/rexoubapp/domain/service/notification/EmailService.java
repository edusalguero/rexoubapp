package com.edusalguero.rexoubapp.domain.service.notification;


public interface EmailService {

    void send(String to, NotificationMessage message);
}
