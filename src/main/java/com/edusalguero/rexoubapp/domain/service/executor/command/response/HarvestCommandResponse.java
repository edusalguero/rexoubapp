package com.edusalguero.rexoubapp.domain.service.executor.command.response;


import com.edusalguero.rexoubapp.domain.model.monitor.harvester.HarvesterType;
import com.edusalguero.rexoubapp.domain.shared.UniqueId;


public class HarvestCommandResponse extends CommandResponse {

    private final UniqueId id;

    public HarvestCommandResponse(UniqueId uniqueId, HarvesterType name) {
        this.id = uniqueId;
        this.name = name;
        this.type = "harvest";
    }

    public UniqueId getId() {
        return id;
    }

    @Override
    public HarvesterType getName() {
        return (HarvesterType) name;
    }


}
