package com.edusalguero.rexoubador.application.auth;

import com.edusalguero.rexoubador.domain.model.user.User;

public interface TokenGenerator {
    String generateToken(User user, String secret);
}
