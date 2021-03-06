package com.edusalguero.rexoubapp.infrastructure.spring.messaging.rabbitmq.consumers;


import com.edusalguero.rexoubapp.domain.event.*;
import com.edusalguero.rexoubapp.domain.event.handler.*;
import com.edusalguero.rexoubapp.domain.service.messaging.MessageConsumer;
import com.edusalguero.rexoubapp.domain.shared.DomainEvent;
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
    private final ServerHarvesterExceededUsageThresholdUsageHandler serverHarvesterExceededUsageThresholdUsageHandler;

    @Autowired
    public RabbitNotificationsConsumer(ServerWasUnreachableHandler serverWasUnreachableHandler, ServerWasReconnectedHandler serverWasReconnectedHandler, ServerObservableWasInactiveHandler serverObservableWasInactiveHandler, ServerObservableStatusWasChangedHandler serverObservableStatusWasChangedHandler, ServerHarvesterExceededUsageThresholdUsageHandler serverHarvesterExceededUsageThresholdUsageHandler) {
        this.serverWasUnreachableHandler = serverWasUnreachableHandler;
        this.serverWasReconnectedHandler = serverWasReconnectedHandler;
        this.serverObservableWasInactiveHandler = serverObservableWasInactiveHandler;
        this.serverObservableStatusWasChangedHandler = serverObservableStatusWasChangedHandler;
        this.serverHarvesterExceededUsageThresholdUsageHandler = serverHarvesterExceededUsageThresholdUsageHandler;
    }

    @RabbitListener(queues = "${rexoubapp.domain.events.notifications-queue-name}")
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
        } else if (event instanceof ServerHarvesterExceededUsageThresholdUsage) {
            serverHarvesterExceededUsageThresholdUsageHandler.handle((ServerHarvesterExceededUsageThresholdUsage) event);
        }
    }
}
