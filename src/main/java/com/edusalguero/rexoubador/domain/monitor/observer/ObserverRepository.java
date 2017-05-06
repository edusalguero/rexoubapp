package com.edusalguero.rexoubador.domain.monitor.observer;

import com.edusalguero.rexoubador.domain.user.User;

import java.util.Collection;


public interface ObserverRepository {

    Observer ofId(ObserverId observerId);

    Collection<Observer> ofUser(User user);

    ObserverId nextIdentity();

    void update(Observer observer);

}
