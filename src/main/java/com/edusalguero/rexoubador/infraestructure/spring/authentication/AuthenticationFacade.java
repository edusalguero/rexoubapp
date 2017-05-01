package com.edusalguero.rexoubador.infraestructure.spring.authentication;

import com.edusalguero.rexoubador.domain.user.UserId;
import com.edusalguero.rexoubador.infraestructure.spring.security.AuthenticatedUser;

import java.security.Principal;

public interface AuthenticationFacade {
    AuthenticatedUser getAuthenticatedUser();
}
