package com.edusalguero.rexoubapp.application.auth;

public class InvalidUsernameOrPassword extends RuntimeException {
    public InvalidUsernameOrPassword() {
        super("Invalid username or password");
    }
}
