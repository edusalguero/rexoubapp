package com.edusalguero.rexoubador.domain.shared;


import com.edusalguero.rexoubador.domain.service.messaging.MessagePublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

    private static final Logger logger = LoggerFactory.getLogger(EventPublisher.class);

    private static MessagePublisher messagePublisher;

    @Autowired
    public EventPublisher(MessagePublisher messagePublisher) {
        EventPublisher.messagePublisher = messagePublisher;
    }

    public static void publish(DomainEvent event) {
        logger.info("Publishing event: " + event.toString());
        messagePublisher.sendEventMessage(event);
    }
}
