package com.edusalguero.rexoubador.application.server;


import com.edusalguero.rexoubador.domain.model.server.ServerId;
import com.edusalguero.rexoubador.domain.model.user.UserId;
import com.edusalguero.rexoubador.domain.shared.Status;

public class ServerUpdateRequest {
    private UserId userId;

    public UserId getUserId() {
        return userId;
    }

    public ServerId getServerId() {
        return serverId;
    }

    private ServerId serverId;
    private String ip;
    private String label;
    private Status status;

    public ServerUpdateRequest(UserId userId, ServerId serverId, String ip, String label, Status status) {
        this.userId = userId;
        this.serverId = serverId;
        this.ip = ip;
        this.label = label;
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public String getLabel() {
        return label;
    }

    public Status getStatus() {
        return status;
    }

}
