package com.edusalguero.rexoubador.domain.service.notification;

public interface SlackService {

    void postMessage(String webhookUrl, String to, String subject, String message);
}
