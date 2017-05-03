package com.edusalguero.rexoubador.infraestructure.spring.authentication;

import com.edusalguero.rexoubador.infraestructure.spring.security.AuthenticatedUser;

public interface AuthenticationFacade {
    AuthenticatedUser getAuthenticatedUser();
}
