package com.edusalguero.rexoubador.infraestructure.spring.controller;


import com.edusalguero.rexoubador.application.auth.LoginResponse;
import com.edusalguero.rexoubador.application.auth.LoginUseCase;
import com.edusalguero.rexoubador.application.user.UserRegistrationUseCase;
import com.edusalguero.rexoubador.domain.model.user.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private UserRegistrationUseCase userRegistrationUseCase;
    @Autowired
    private LoginUseCase loginUseCase;

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
