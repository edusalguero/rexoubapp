package com.edusalguero.rexoubador.infraestructure.spring.controller;

import com.edusalguero.rexoubador.application.event.EventResponse;
import com.edusalguero.rexoubador.application.event.UserEventsUseCase;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/v1/events", produces = "application/json")
@Api(description="Authenticated user's events")
public class EventController extends AuthenticatedUserController {

    private final UserEventsUseCase userEventsUseCase;

    @Autowired
    public EventController(UserEventsUseCase userEventsUseCase) {
        this.userEventsUseCase = userEventsUseCase;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ArrayList<EventResponse> list(@RequestParam(value="days", defaultValue="30") int days) {

        return userEventsUseCase.execute(getAuthenticatedUserId(), days);
    }

}
