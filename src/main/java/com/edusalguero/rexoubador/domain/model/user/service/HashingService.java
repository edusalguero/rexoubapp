package com.edusalguero.rexoubador.domain.model.user.service;

public interface HashingService {

    String hash(String password);

    boolean matches(String password, String encodedPassword);
}
