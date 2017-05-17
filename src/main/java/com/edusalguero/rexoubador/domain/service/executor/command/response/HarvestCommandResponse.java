package com.edusalguero.rexoubador.domain.service.executor.command.response;


import com.edusalguero.rexoubador.domain.model.monitor.MonitorDataInterface;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.HarvesterType;
import com.edusalguero.rexoubador.domain.shared.UniqueId;

import java.util.HashMap;


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
