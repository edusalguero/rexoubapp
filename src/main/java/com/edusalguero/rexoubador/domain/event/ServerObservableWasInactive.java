package com.edusalguero.rexoubador.domain.event;


import com.edusalguero.rexoubador.domain.model.server.ServerId;
import com.edusalguero.rexoubador.domain.model.server.observer.ServerObserverId;
import com.edusalguero.rexoubador.domain.model.user.UserId;
import com.edusalguero.rexoubador.domain.shared.DomainEvent;

import java.util.Date;

public class ServerObservableWasInactive extends DomainEvent {
    private ServerId serverId;
    private UserId userId;
    private ServerObserverId serverObserverId;

    public ServerObservableWasInactive(ServerId serverId, UserId userId, ServerObserverId serverObserverId) {
        super(new Date());
        this.serverId = serverId;
        this.userId = userId;
        this.serverObserverId = serverObserverId;
    }

    @Override
    public String forTopic() {
        return "notificationsQueue";
    }

    public ServerId getServerId() {
        return serverId;
    }

    public UserId getUserId() {
        return userId;
    }

    public ServerObserverId getServerObserverId() {
        return serverObserverId;
    }
}
