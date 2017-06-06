package com.edusalguero.rexoubapp.domain.model.monitor.observer;

import com.edusalguero.rexoubapp.domain.model.user.User;

import java.util.Collection;


public interface ObserverRepository {

    Observer ofId(ObserverId observerId);

    Collection<Observer> ofUser(User user);

    ObserverId nextIdentity();

    void update(Observer observer);

}
