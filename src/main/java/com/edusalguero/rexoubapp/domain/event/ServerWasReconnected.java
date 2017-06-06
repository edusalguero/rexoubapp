package com.edusalguero.rexoubapp.domain.event;

import com.edusalguero.rexoubapp.domain.model.server.ServerId;
import com.edusalguero.rexoubapp.domain.model.user.UserId;
import com.edusalguero.rexoubapp.domain.shared.DomainEvent;

import java.util.Date;


public class ServerWasReconnected extends DomainEvent {
    private ServerId serverId;
    private UserId userId;

    public void setServerId(ServerId serverId) {
        this.serverId = serverId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }

    public ServerWasReconnected(ServerId serverId, UserId userId) {
        super(new Date());
        this.serverId = serverId;
        this.userId = userId;
    }

    public ServerId getServerId() {
        return serverId;
    }

    public UserId getUserId() {
        return userId;
    }

    @Override
    public String forTopic() {
        return "notificationsQueue";
    }


}
