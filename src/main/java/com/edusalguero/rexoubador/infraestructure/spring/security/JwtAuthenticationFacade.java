package com.edusalguero.rexoubador.infraestructure.spring.security;

import com.edusalguero.rexoubador.infraestructure.spring.authentication.AuthenticationFacade;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class JwtAuthenticationFacade implements AuthenticationFacade {
    @Override
    public AuthenticatedUser getAuthenticatedUser() {
        return (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
