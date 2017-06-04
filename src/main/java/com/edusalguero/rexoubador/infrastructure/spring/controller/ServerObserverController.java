package com.edusalguero.rexoubador.infrastructure.spring.controller;

import com.edusalguero.rexoubador.application.server.observer.*;
import com.edusalguero.rexoubador.domain.model.monitor.observer.ObserverId;
import com.edusalguero.rexoubador.domain.model.server.ServerId;
import com.edusalguero.rexoubador.domain.model.server.observer.ServerObserverId;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/v1/servers/{serverId}/observers", produces = "application/json")
@Api(description="Server observers management operations")
public class ServerObserverController extends AuthenticatedUserController {

    private final ServerObserversUseCase serverObserversUseCase;

    private final ServerObserverCreateUseCase serverObserverCreateUseCase;

    private final ServerObserverInformationUseCase serverObserverInformationUseCase;

    private final ServerObserverDeleteUseCase serverObserverDeleteUseCase;

    @Autowired
    public ServerObserverController(ServerObserversUseCase serverObserversUseCase, ServerObserverCreateUseCase serverObserverCreateUseCase, ServerObserverInformationUseCase serverObserverInformationUseCase, ServerObserverDeleteUseCase serverObserverDeleteUseCase) {
        this.serverObserversUseCase = serverObserversUseCase;
        this.serverObserverCreateUseCase = serverObserverCreateUseCase;
        this.serverObserverInformationUseCase = serverObserverInformationUseCase;
        this.serverObserverDeleteUseCase = serverObserverDeleteUseCase;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ArrayList<ServerObserverResponse> list(@PathVariable String serverId) {
        return serverObserversUseCase.execute(getAuthenticatedUserId(), new ServerId(serverId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ServerObserverId add(@PathVariable String serverId,
                                @RequestParam(value = "observerId") String observerId) {
        return serverObserverCreateUseCase.execute(getAuthenticatedUserId(), new ServerId(serverId), new ObserverId(observerId));
    }

    @RequestMapping(path = "/{serverObserverId}", method = RequestMethod.GET)
    public ServerObserverResponse show(@PathVariable String serverId,
                                       @PathVariable String serverObserverId) {
        return serverObserverInformationUseCase.execute(getAuthenticatedUserId(), new ServerId(serverId), new ServerObserverId(serverObserverId));
    }

    @RequestMapping(path = "/{serverObserverId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String serverId,
                       @PathVariable String serverObserverId) {
        serverObserverDeleteUseCase.execute(getAuthenticatedUserId(), new ServerId(serverId), new ServerObserverId(serverObserverId));
    }
}
