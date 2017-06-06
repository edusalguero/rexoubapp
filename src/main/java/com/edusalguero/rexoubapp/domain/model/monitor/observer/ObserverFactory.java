package com.edusalguero.rexoubapp.domain.model.monitor.observer;

import com.edusalguero.rexoubapp.domain.model.user.User;
import com.edusalguero.rexoubapp.domain.shared.Status;

public class ObserverFactory {

    public Observer make(User user, ObserverId observerId, ObserverType type, String name, String label, Boolean notifyStatusChanges, Boolean notifyInactivity, Status status) {
        if (type == ObserverType.SERVICE) {
            return new Service(user, observerId, name, label, notifyStatusChanges, notifyInactivity, status);
        }
        throw new InvalidObserverTypeException();
    }
}
