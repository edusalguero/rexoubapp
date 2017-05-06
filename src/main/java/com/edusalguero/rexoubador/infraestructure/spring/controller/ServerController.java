package com.edusalguero.rexoubador.infraestructure.spring.controller;

import com.edusalguero.rexoubador.application.server.*;
import com.edusalguero.rexoubador.domain.Status;
import com.edusalguero.rexoubador.domain.server.ServerId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/servers", produces = "application/json")
public class ServerController extends AuthenticatedUserController {

    @Autowired
    private UserServersUseCase userServersUseCase;

    @Autowired
    private ServerCreateUserCase serverCreateUserCase;

    @Autowired
    private ServerInformationUseCase serverInformationUseCase;

    @Autowired
    private ServerDeleteUseCase serverDeleteUseCase;

    @Autowired
    private ServerUpdateUseCase serverUpdateUseCase;

    @Autowired
    private ServerUptimeUseCase serverUptimeUseCase;

    @RequestMapping(method = RequestMethod.GET)
    public ArrayList<ServerResponse> list() {
        return userServersUseCase.execute(getAuthenticatedUserId());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ServerCreateResponse add(@RequestParam(value = "label") String label,
                                    @RequestParam(value = "ip") String ip,
                                    @RequestParam(value = "status", required = false, defaultValue = "ENABLED") Status status) {
        return serverCreateUserCase.execute(getAuthenticatedUserId(), label, ip, status);
    }


    @RequestMapping(path = "/{serverId}", method = RequestMethod.GET)
    public ServerResponse view(@PathVariable String serverId) {
        return serverInformationUseCase.execute(new ServerId(serverId), getAuthenticatedUserId());
    }

    @RequestMapping(path = "/{serverId}", method = RequestMethod.PUT)
    public void update(@PathVariable String serverId,
                       @RequestParam(value = "label") String label,
                       @RequestParam(value = "ip") String ip,
                       @RequestParam(value = "status", required = false, defaultValue = "ENABLED") Status status
    ) {
        ServerUpdateRequest serverUpdateRequest = new ServerUpdateRequest(getAuthenticatedUserId(), new ServerId(serverId), ip, label, status);
        serverUpdateUseCase.execute(serverUpdateRequest);
    }

    @RequestMapping(path = "/{serverId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String serverId) {
        serverDeleteUseCase.execute(new ServerId(serverId), getAuthenticatedUserId());
    }

    @RequestMapping(path = "/{serverId}/uptime", method = RequestMethod.GET)
    public ServerUptimeResponse uptime(@PathVariable String serverId) {
        return serverUptimeUseCase.execute(new ServerId(serverId), getAuthenticatedUserId());
    }
}
