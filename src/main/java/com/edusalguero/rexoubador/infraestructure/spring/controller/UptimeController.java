package com.edusalguero.rexoubador.infraestructure.spring.controller;

import com.edusalguero.rexoubador.application.server.ServerUptimeResponse;
import com.edusalguero.rexoubador.application.server.ServersUptimeUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/uptime", produces = "application/json")
public class UptimeController extends AuthenticatedUserController {

    @Autowired
    private ServersUptimeUseCase serversUptimeUseCase;


    @RequestMapping(method = RequestMethod.GET)
    public ArrayList<ServerUptimeResponse> list() {
        return serversUptimeUseCase.execute(getAuthenticatedUserId());
    }

}