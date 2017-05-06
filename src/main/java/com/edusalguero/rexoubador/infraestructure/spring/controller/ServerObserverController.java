package com.edusalguero.rexoubador.infraestructure.spring.controller;

import com.edusalguero.rexoubador.application.server.observer.*;
import com.edusalguero.rexoubador.domain.monitor.observer.ObserverId;
import com.edusalguero.rexoubador.domain.server.observer.ServerObserverId;
import com.edusalguero.rexoubador.domain.server.ServerId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/servers/{serverId}/observers", produces = "application/json")
public class ServerObserverController extends AuthenticatedUserController {

    @Autowired
    private ServerObserversUseCase serverObserversUseCase;

    @Autowired
    private ServerObserverCreateUseCase serverObserverCreateUseCase;

    @Autowired
    private ServerObserverInformationUseCase serverObserverInformationUseCase;

    @Autowired
    private ServerObserverDeleteUseCase serverObserverDeleteUseCase;

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
