package com.edusalguero.rexoubapp.domain.shared;


public interface EventHandler<T extends DomainEvent> {

    void handle(T event);
}
