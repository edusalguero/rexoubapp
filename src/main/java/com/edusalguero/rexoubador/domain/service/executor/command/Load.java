package com.edusalguero.rexoubador.domain.service.executor.command;

import com.edusalguero.rexoubador.domain.model.monitor.harvester.HarvesterType;
import com.edusalguero.rexoubador.domain.service.executor.command.response.HarvestCommandResponse;
import com.edusalguero.rexoubador.domain.shared.UniqueId;

public class Load implements CommandInterface {

    private UniqueId id;

    public Load(UniqueId id) {
        this.id = id;
    }

    @Override
    public String getCommandString() {
        return "cat /proc/loadavg";
    }

    @Override
    public HarvestCommandResponse parseResult(String result) {
        String[] parts = result.split(" ");
        HarvestCommandResponse harvestCommandResponse = new HarvestCommandResponse(id, HarvesterType.LOAD);
        harvestCommandResponse.addData("last", parts[0]);
        harvestCommandResponse.addData("5min", parts[1]);
        harvestCommandResponse.addData("15min", parts[2]);
        return harvestCommandResponse;
    }


}
