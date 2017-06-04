package com.edusalguero.rexoubador.infrastructure.spring.authentication;

import com.edusalguero.rexoubador.infrastructure.spring.security.AuthenticatedUser;

public interface AuthenticationFacade {
    AuthenticatedUser getAuthenticatedUser();
}
