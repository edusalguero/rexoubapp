package com.edusalguero.rexoubador.domain.shared;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}
