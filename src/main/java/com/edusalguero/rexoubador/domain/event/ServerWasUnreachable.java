package com.edusalguero.rexoubador.domain.event;

import com.edusalguero.rexoubador.domain.model.server.ServerId;
import com.edusalguero.rexoubador.domain.model.user.UserId;
import com.edusalguero.rexoubador.domain.shared.DomainEvent;

import java.util.Date;


public class ServerWasUnreachable extends DomainEvent {
    private ServerId serverId;
    private UserId userId;

    public void setServerId(ServerId serverId) {
        this.serverId = serverId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }

    public ServerWasUnreachable(ServerId serverId, UserId userId) {
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
