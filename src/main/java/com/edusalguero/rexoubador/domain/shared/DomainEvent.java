package com.edusalguero.rexoubador.domain.shared;


import java.util.Date;

public abstract class DomainEvent implements Event {
    private Date createdOn;
    private transient String topic;

    public DomainEvent(Date createdOn) {
        this.setCreatedOn(createdOn);
        this.setTopic(forTopic());
    }

    public Date occurredOn() {
        return createdOn;
    }

    private void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    private void setTopic(String topic) {
        this.topic = topic;
    }
}
