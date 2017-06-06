package com.edusalguero.rexoubapp.infrastructure.spring.controller;

import com.edusalguero.rexoubapp.application.server.*;
import com.edusalguero.rexoubapp.domain.model.server.ServerId;
import com.edusalguero.rexoubapp.domain.shared.Status;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/v1/servers", produces = "application/json")
@Api(description="Authenticated user' servers management operations")
public class ServerController extends AuthenticatedUserController {

    private final UserServersUseCase userServersUseCase;

    private final ServerCreateUserCase serverCreateUserCase;

    private final ServerInformationUseCase serverInformationUseCase;

    private final ServerDeleteUseCase serverDeleteUseCase;

    private final ServerUpdateUseCase serverUpdateUseCase;

    private final ServerUptimeUseCase serverUptimeUseCase;

    @Autowired
    public ServerController(UserServersUseCase userServersUseCase, ServerCreateUserCase serverCreateUserCase, ServerInformationUseCase serverInformationUseCase, ServerDeleteUseCase serverDeleteUseCase, ServerUpdateUseCase serverUpdateUseCase, ServerUptimeUseCase serverUptimeUseCase) {
        this.userServersUseCase = userServersUseCase;
        this.serverCreateUserCase = serverCreateUserCase;
        this.serverInformationUseCase = serverInformationUseCase;
        this.serverDeleteUseCase = serverDeleteUseCase;
        this.serverUpdateUseCase = serverUpdateUseCase;
        this.serverUptimeUseCase = serverUptimeUseCase;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ArrayList<ServerSummaryResponse> list() {
        return userServersUseCase.execute(getAuthenticatedUserId());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ServerCreateResponse add(@RequestParam(value = "label") String label,
                                    @RequestParam(value = "ip") String ip,
                                    @RequestParam(value = "status", required = false, defaultValue = "ENABLED") Status status) {
        return serverCreateUserCase.execute(getAuthenticatedUserId(), label, ip, status);
    }


    @RequestMapping(path = "/{serverId}", method = RequestMethod.GET)
    public ServerFullResponse view(@PathVariable String serverId) {
        return serverInformationUseCase.execute(new ServerId(serverId), getAuthenticatedUserId());
    }

    @RequestMapping(path = "/{serverId}", method = RequestMethod.PUT)
    public void update(@PathVariable String serverId,
                       @RequestParam(value = "label") String label,
                       @RequestParam(value = "ip", defaultValue = "") String ip,
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
