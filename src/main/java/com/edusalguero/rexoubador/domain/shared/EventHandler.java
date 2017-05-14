package com.edusalguero.rexoubador.domain.shared;


public interface EventHandler<T extends DomainEvent> {

    void handle(T event);
}
