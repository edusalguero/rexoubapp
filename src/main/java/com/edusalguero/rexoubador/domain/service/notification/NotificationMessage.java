package com.edusalguero.rexoubador.domain.service.notification;


public interface NotificationMessage {
    String getSubject();

    String getBody();

    String getTextBody();

    String getMarkdownBody();
}
