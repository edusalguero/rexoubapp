package com.edusalguero.rexoubapp.infrastructure.spring.authentication;

import com.edusalguero.rexoubapp.infrastructure.spring.security.AuthenticatedUser;

public interface AuthenticationFacade {
    AuthenticatedUser getAuthenticatedUser();
}
