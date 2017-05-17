package com.edusalguero.rexoubador.infraestructure.spring.messaging.rabbitmq.consumers;


import com.edusalguero.rexoubador.domain.event.ServerObservableStatusWasChanged;
import com.edusalguero.rexoubador.domain.event.ServerObservableWasInactive;
import com.edusalguero.rexoubador.domain.event.ServerWasReconnected;
import com.edusalguero.rexoubador.domain.event.ServerWasUnreachable;
import com.edusalguero.rexoubador.domain.event.handler.ServerObservableStatusWasChangedHandler;
import com.edusalguero.rexoubador.domain.event.handler.ServerObservableWasInactiveHandler;
import com.edusalguero.rexoubador.domain.event.handler.ServerWasReconnectedHandler;
import com.edusalguero.rexoubador.domain.event.handler.ServerWasUnreachableHandler;
import com.edusalguero.rexoubador.domain.service.messaging.MessageConsumer;
import com.edusalguero.rexoubador.domain.shared.DomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitNotificationsConsumer implements MessageConsumer<DomainEvent> {
    private static final Logger logger = LoggerFactory.getLogger(RabbitNotificationsConsumer.class);

    private final ServerWasUnreachableHandler serverWasUnreachableHandler;
    private final ServerWasReconnectedHandler serverWasReconnectedHandler;
    private final ServerObservableWasInactiveHandler serverObservableWasInactiveHandler;
    private final ServerObservableStatusWasChangedHandler serverObservableStatusWasChangedHandler;

    @Autowired
    public RabbitNotificationsConsumer(ServerWasUnreachableHandler serverWasUnreachableHandler, ServerWasReconnectedHandler serverWasReconnectedHandler, ServerObservableWasInactiveHandler serverObservableWasInactiveHandler, ServerObservableStatusWasChangedHandler serverObservableStatusWasChangedHandler) {
        this.serverWasUnreachableHandler = serverWasUnreachableHandler;
        this.serverWasReconnectedHandler = serverWasReconnectedHandler;
        this.serverObservableWasInactiveHandler = serverObservableWasInactiveHandler;
        this.serverObservableStatusWasChangedHandler = serverObservableStatusWasChangedHandler;
    }

    @RabbitListener(queues = "${rexoubador.domain.events.notifications-queue-name}")
    @Override
    public void receiveMessage(DomainEvent event) {
        logger.info("Receiving event " + event);
        if (event instanceof ServerObservableStatusWasChanged) {
            serverObservableStatusWasChangedHandler.handle((ServerObservableStatusWasChanged) event);
        } else if (event instanceof ServerObservableWasInactive) {
            serverObservableWasInactiveHandler.handle((ServerObservableWasInactive) event);
        } else if (event instanceof ServerWasUnreachable) {
            serverWasUnreachableHandler.handle((ServerWasUnreachable) event);
        } else if (event instanceof ServerWasReconnected) {
            serverWasReconnectedHandler.handle((ServerWasReconnected) event);
        }
    }
}
