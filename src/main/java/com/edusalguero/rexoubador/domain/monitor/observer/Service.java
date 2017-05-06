package com.edusalguero.rexoubador.domain.monitor.observer;

import com.edusalguero.rexoubador.domain.user.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SERVICE")
public class Service extends Observer {
    public Service(User user, ObserverId observerId, String name, String label, Boolean notifyStatusChanges, Boolean notifyInactivity) {
        super(user, observerId, name, label, notifyStatusChanges, notifyInactivity);
        type = ObserverType.SERVICE;
    }
    protected Service()
    {
        // Needed by JPA
    }
}
