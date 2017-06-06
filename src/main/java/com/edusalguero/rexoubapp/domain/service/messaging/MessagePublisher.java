package com.edusalguero.rexoubapp.domain.service.messaging;


import com.edusalguero.rexoubapp.domain.shared.DomainEvent;

public interface MessagePublisher<T extends DomainEvent> {
    void sendEventMessage(T event);
}
