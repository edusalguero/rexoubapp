package com.edusalguero.rexoubador.domain.service.messaging;

import com.edusalguero.rexoubador.domain.shared.DomainEvent;

public interface MessageConsumer<T extends DomainEvent> {
     void receiveMessage(T event);
}
