package com.edusalguero.rexoubador.infraestructure.spring.controller;

import com.edusalguero.rexoubador.domain.model.user.UserId;
import com.edusalguero.rexoubador.infraestructure.spring.security.JwtAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
abstract public class AuthenticatedUserController {
    @Autowired
    private JwtAuthenticationFacade authenticationFacade;

    protected UserId getAuthenticatedUserId() {
        return new UserId(authenticationFacade.getAuthenticatedUser().getId());
    }
}
