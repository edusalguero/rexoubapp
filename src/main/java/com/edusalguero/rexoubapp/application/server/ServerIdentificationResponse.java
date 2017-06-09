package com.edusalguero.rexoubapp.application.server;


import com.edusalguero.rexoubapp.domain.model.server.Server;

public class ServerIdentificationResponse {
    private final String id;
    private final String label;

    public ServerIdentificationResponse(Server server) {
        this.id = server.id();
        this.label = server.label();
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }
}
