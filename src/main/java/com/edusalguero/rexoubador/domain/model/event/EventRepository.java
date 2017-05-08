package com.edusalguero.rexoubador.domain.model.event;

import com.edusalguero.rexoubador.domain.model.user.User;

import java.util.Collection;


public interface EventRepository {

    Collection<Event> ofUser(User user);

    EventId nextIdentity();

    void save(Event event);
}
