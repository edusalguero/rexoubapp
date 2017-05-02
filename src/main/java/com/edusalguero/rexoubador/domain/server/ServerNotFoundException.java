package com.edusalguero.rexoubador.domain.server;

public class ServerNotFoundException extends RuntimeException {
    public ServerNotFoundException() {
        super("Server not found!");
    }
}
