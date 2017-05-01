package com.edusalguero.rexoubador.domain.user.service;

import org.springframework.stereotype.Service;

@Service
public interface HashingService {

    String hash(String password);

    boolean matches(String password, String encodedPassword);
}
