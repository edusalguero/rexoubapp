package com.edusalguero.rexoubador.infraestructure.spring.messaging.rabbitmq;

import com.edusalguero.rexoubador.domain.service.messaging.MessagePublisher;
import com.edusalguero.rexoubador.domain.shared.DomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMessagePublisher implements MessagePublisher {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMessagePublisher.class);

    private final String exchangeName;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMessagePublisher(@Value("${rexoubador.domain.events.exchange-name}") String exchangeName, RabbitTemplate rabbitTemplate) {
        this.exchangeName = exchangeName;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendEventMessage(DomainEvent event) {
        logger.info("Sending message: " + event.getClass());
        rabbitTemplate.convertAndSend(exchangeName, event.forTopic(), event);
    }
}
