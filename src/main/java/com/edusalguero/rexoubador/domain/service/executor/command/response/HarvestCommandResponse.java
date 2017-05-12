package com.edusalguero.rexoubador.domain.service.executor.command.response;


import com.edusalguero.rexoubador.domain.model.monitor.harvester.HarvesterType;
import com.edusalguero.rexoubador.domain.shared.UniqueId;

import java.util.HashMap;


public class HarvestCommandResponse extends CommandResponse {

    private final UniqueId id;

    public HarvestCommandResponse(UniqueId uniqueId, HarvesterType name) {
        id =  uniqueId;
        result.put("type", "harvest");
        result.put("name", name);
        result.put("data", new HashMap<String, Object>());
    }

    public UniqueId getId() {
        return id;
    }

    @Override
    public HarvesterType getName() {
        return (HarvesterType) result.get("name");
    }
}
