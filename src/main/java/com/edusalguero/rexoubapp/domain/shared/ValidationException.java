package com.edusalguero.rexoubapp.domain.shared;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}
