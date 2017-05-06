package com.edusalguero.rexoubador.domain.monitor.observer;

import com.edusalguero.rexoubador.domain.user.User;

public class ObserverFactory {

    public Observer make(User user, ObserverId observerId, ObserverType type, String name, String label, Boolean notifyStatusChanges, Boolean notifyInactivity) {
        if (type == ObserverType.SERVICE) {
            return new Service(user, observerId, name, label, notifyStatusChanges, notifyInactivity);
        }
        throw new InvalidObserverTypeException();
    }
}
