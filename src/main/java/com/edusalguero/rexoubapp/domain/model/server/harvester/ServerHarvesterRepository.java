package com.edusalguero.rexoubapp.domain.model.server.harvester;

import com.edusalguero.rexoubapp.domain.model.server.Server;

import java.util.Collection;


public interface ServerHarvesterRepository {

    ServerHarvester ofId(ServerHarvesterId serverHarvesterId);

    Collection<ServerHarvester> ofServer(Server server);

    ServerHarvesterId nextIdentity();


}
