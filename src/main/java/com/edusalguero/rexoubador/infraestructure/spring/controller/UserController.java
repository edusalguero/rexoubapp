package com.edusalguero.rexoubador.infraestructure.spring.controller;


import com.edusalguero.rexoubador.application.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/users/self", produces = "application/json")
public class UserController extends AuthenticatedUserController {

    private final UserInformationUseCase userInformationUseCase;

    private final UserUpdateUseCase userUpdateUseCase;

    private final UserDeleteUseCase userDeleteUseCase;

    @Autowired
    public UserController(UserInformationUseCase userInformationUseCase, UserUpdateUseCase userUpdateUseCase, UserDeleteUseCase userDeleteUseCase) {
        this.userInformationUseCase = userInformationUseCase;
        this.userUpdateUseCase = userUpdateUseCase;
        this.userDeleteUseCase = userDeleteUseCase;
    }

    @RequestMapping(method = RequestMethod.GET)
    public UserInformationResponse show(HttpServletRequest request) {
        return userInformationUseCase.execute(getAuthenticatedUserId());
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestParam(value = "password", required = false) String password,
                       @RequestParam(value = "firstName", required = false) String firstName,
                       @RequestParam(value = "lastName", required = false) String lastName
    ) {
        UserUpdateRequest updateRequest = new UserUpdateRequest(getAuthenticatedUserId(), password, firstName, lastName);
        userUpdateUseCase.execute(updateRequest);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete() {
        userDeleteUseCase.execute(getAuthenticatedUserId());
    }

}
