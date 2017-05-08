package com.edusalguero.rexoubador.infraestructure.spring.controller;

import com.edusalguero.rexoubador.application.server.harvester.*;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.HarvesterId;
import com.edusalguero.rexoubador.domain.model.server.ServerId;
import com.edusalguero.rexoubador.domain.model.server.harvester.ServerHarvesterId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/servers/{serverId}/harvesters", produces = "application/json")
public class ServerHarvesterController extends AuthenticatedUserController {

    @Autowired
    private ServerHarvestersUseCase serverHarvestersUseCase;

    @Autowired
    private ServerHarvesterCreateUseCase serverHarvesterCreateUseCase;

    @Autowired
    private ServerHarvesterInformationUseCase serverHarvesterInformationUseCase;

    @Autowired
    private ServerHarvesterDeleteUseCase serverHarvesterDeleteUseCase;

    @RequestMapping(method = RequestMethod.GET)
    public ArrayList<ServerHarvesterResponse> list(@PathVariable String serverId) {
        return serverHarvestersUseCase.execute(getAuthenticatedUserId(), new ServerId(serverId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ServerHarvesterId add(@PathVariable String serverId,
                                 @RequestParam(value = "harvesterId") String harvesterId) {
        return serverHarvesterCreateUseCase.execute(getAuthenticatedUserId(), new ServerId(serverId), new HarvesterId(harvesterId));
    }

    @RequestMapping(path = "/{serverHarvesterId}", method = RequestMethod.GET)
    public ServerHarvesterResponse show(@PathVariable String serverId,
                                        @PathVariable String serverHarvesterId) {
        return serverHarvesterInformationUseCase.execute(getAuthenticatedUserId(), new ServerId(serverId), new ServerHarvesterId(serverHarvesterId));
    }

    @RequestMapping(path = "/{serverHarvesterId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String serverId,
                       @PathVariable String serverHarvesterId) {
        serverHarvesterDeleteUseCase.execute(getAuthenticatedUserId(), new ServerId(serverId), new ServerHarvesterId(serverHarvesterId));
    }
}
