package com.edusalguero.rexoubador.domain.server.harvester;

import com.edusalguero.rexoubador.domain.server.Server;

import java.util.Collection;


public interface ServerHarvesterRepository {

    ServerHarvester ofId(ServerHarvesterId serverHarvesterId);

    Collection<ServerHarvester> ofServer(Server server);

    ServerHarvesterId nextIdentity();


}
