package com.edusalguero.rexoubapp.application.auth;

import com.edusalguero.rexoubapp.domain.model.user.User;

public interface TokenGenerator {
    String generateToken(User user, String secret);
}
