package com.edusalguero.rexoubador.infrastructure.spring.controller;


import com.edusalguero.rexoubador.application.auth.LoginResponse;
import com.edusalguero.rexoubador.application.auth.LoginUseCase;
import com.edusalguero.rexoubador.application.user.UserRegistrationUseCase;
import com.edusalguero.rexoubador.domain.model.user.UserId;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@Api(description="Login and register")
public class AuthController {


    private final UserRegistrationUseCase userRegistrationUseCase;
    private final LoginUseCase loginUseCase;

    @Autowired
    public AuthController(UserRegistrationUseCase userRegistrationUseCase, LoginUseCase loginUseCase) {
        this.userRegistrationUseCase = userRegistrationUseCase;
        this.loginUseCase = loginUseCase;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/register", produces = "application/json")
    public UserId register(@RequestParam(value = "username") String username,
                           @RequestParam(value = "password") String password,
                           @RequestParam(value = "firstName") String firstName,
                           @RequestParam(value = "lastName") String lastName) {
        return userRegistrationUseCase.execute(username, password, firstName, lastName);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login", produces = "application/json")
    public LoginResponse login(@RequestParam(value = "username") String username,
                               @RequestParam(value = "password") String password) {

        return loginUseCase.execute(username, password);

    }

}
