package com.edusalguero.rexoubapp.domain.service.messaging;

import com.edusalguero.rexoubapp.domain.shared.DomainEvent;

public interface MessageConsumer<T extends DomainEvent> {
    void receiveMessage(T event);
}
