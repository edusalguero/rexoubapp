package com.edusalguero.rexoubador.domain.event;


import com.edusalguero.rexoubador.domain.model.server.ServerId;
import com.edusalguero.rexoubador.domain.model.server.observer.ServerObserverId;
import com.edusalguero.rexoubador.domain.model.user.UserId;
import com.edusalguero.rexoubador.domain.shared.CheckStatus;
import com.edusalguero.rexoubador.domain.shared.DomainEvent;

import java.util.Date;

public class ServerObservableStatusWasChanged extends DomainEvent {
    private CheckStatus checkStatus;
    private ServerId serverId;
    private UserId userId;
    private ServerObserverId serverObserverId;

    public ServerObservableStatusWasChanged(ServerId serverId, UserId userId, ServerObserverId serverObserverId, CheckStatus checkStatus) {
        super(new Date());
        this.serverId = serverId;
        this.userId = userId;
        this.serverObserverId = serverObserverId;
        this.checkStatus = checkStatus;
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

    public CheckStatus getCheckStatus() {
        return checkStatus;
    }
}
