package com.edusalguero.rexoubador.infraestructure.spring.controller;

import com.edusalguero.rexoubador.application.server.ServerUptimeResponse;
import com.edusalguero.rexoubador.application.server.ServersUptimeUseCase;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/v1/uptime", produces = "application/json")
@Api(description="Servers uptime list")
public class UptimeController extends AuthenticatedUserController {

    private final ServersUptimeUseCase serversUptimeUseCase;

    @Autowired
    public UptimeController(ServersUptimeUseCase serversUptimeUseCase) {
        this.serversUptimeUseCase = serversUptimeUseCase;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ArrayList<ServerUptimeResponse> list() {
        return serversUptimeUseCase.execute(getAuthenticatedUserId());
    }

}
