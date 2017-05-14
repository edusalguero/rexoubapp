package com.edusalguero.rexoubador.domain.service.messaging;


import com.edusalguero.rexoubador.domain.shared.DomainEvent;

public interface MessagePublisher<T extends DomainEvent> {
    void sendEventMessage(T event);
}
