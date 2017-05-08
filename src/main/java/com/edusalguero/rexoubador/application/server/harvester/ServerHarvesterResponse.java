package com.edusalguero.rexoubador.application.server.harvester;

import com.edusalguero.rexoubador.application.monitor.harvester.HarvesterResponse;
import com.edusalguero.rexoubador.domain.model.server.harvester.Harvest;
import com.edusalguero.rexoubador.domain.model.server.harvester.ServerHarvester;

public class ServerHarvesterResponse {
    private HarvesterResponse harvesterResponse;
    private Harvest harvest;
    private String serverHarvesterId;

    public ServerHarvesterResponse(ServerHarvester serverHarvester)
    {
        this.harvesterResponse = new HarvesterResponse(serverHarvester.harvester());
        this.harvest = serverHarvester.getLastHarvest();
        this.serverHarvesterId = serverHarvester.id();
    }

    public String getServerHarvesterId() {
        return serverHarvesterId;
    }

    public Harvest getHarvest() {
        return harvest;
    }

    public HarvesterResponse getHarvesterResponse() {
        return harvesterResponse;
    }
}
