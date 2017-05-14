package com.edusalguero.rexoubador.infraestructure.service.notification;

import com.edusalguero.rexoubador.domain.model.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationSenderService implements com.edusalguero.rexoubador.domain.service.NotificationSenderService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationSenderService.class);

    @Override
    public void send(Event event) {
        logger.info(String.format("Sending notification [%s] to [%s] recipients.", event.id(),event.contacts().size()));
        logger.debug("Notification message: "+ event.message());
    }
}
