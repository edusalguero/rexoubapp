package com.edusalguero.rexoubapp.application.server.harvester;

import com.edusalguero.rexoubapp.application.datatransformer.DateConverter;
import com.edusalguero.rexoubapp.application.monitor.harvester.HarvesterResponse;
import com.edusalguero.rexoubapp.domain.model.server.harvester.Harvest;
import com.edusalguero.rexoubapp.domain.model.server.harvester.ServerHarvester;

public class ServerHarvesterResponse {
    private HarvesterResponse harvesterResponse;
    private Harvest harvest;
    private String serverHarvesterId;

    public ServerHarvesterResponse(ServerHarvester serverHarvester) {
        this.harvesterResponse = new HarvesterResponse(serverHarvester.harvester());
        this.harvest = serverHarvester.getLastHarvest();
        this.serverHarvesterId = serverHarvester.id();
    }

    public String getHarvestDate() {
        return DateConverter.getFormattedDateOrEmptyString(harvest.getDate());
    }

    public Harvest getHarvest() {
        return harvest;
    }

    public String getServerHarvesterId() {
        return serverHarvesterId;
    }


    public HarvesterResponse getHarvester() {
        return harvesterResponse;
    }
}
