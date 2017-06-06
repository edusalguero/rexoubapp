package com.edusalguero.rexoubapp.domain.model.user;


public class UndefinedOrInvalidHashingService extends RuntimeException {
    public UndefinedOrInvalidHashingService() {
        super("Undefined or invalid Hashing service!");
    }
}
