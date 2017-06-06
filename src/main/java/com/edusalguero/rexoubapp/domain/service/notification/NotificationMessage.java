package com.edusalguero.rexoubapp.domain.service.notification;


public interface NotificationMessage {
    String getSubject();

    String getBody();

    String getTextBody();

    String getMarkdownBody();
}
