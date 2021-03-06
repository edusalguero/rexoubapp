package com.edusalguero.rexoubapp.application.server;

import com.edusalguero.rexoubapp.domain.model.server.ServerId;

public class ServerCreateResponse {

    private String serverId;
    private String publicSSHKey;

    ServerCreateResponse(ServerId serverId, String publicSSHKey) {
        this.serverId = serverId.toString();
        this.publicSSHKey = publicSSHKey;
    }

    public String getServerId() {
        return serverId;
    }

    public String getPublicSSHKey() {
        return publicSSHKey;
    }
}
