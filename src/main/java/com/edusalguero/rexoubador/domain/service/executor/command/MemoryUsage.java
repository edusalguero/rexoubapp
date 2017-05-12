package com.edusalguero.rexoubador.domain.service.executor.command;

import com.edusalguero.rexoubador.domain.model.monitor.harvester.HarvesterType;
import com.edusalguero.rexoubador.domain.service.executor.command.response.HarvestCommandResponse;
import com.edusalguero.rexoubador.domain.shared.UniqueId;

public class MemoryUsage implements CommandInterface {

    private UniqueId id;

    public MemoryUsage(UniqueId id) {
        this.id = id;
    }

    @Override
    public String getCommandString() {
        return "free -k | grep Mem";
    }

    @Override
    public HarvestCommandResponse parseResult(String result) {
        String[] parts = result.split("\\s+");

        HarvestCommandResponse harvestCommandResponse = new HarvestCommandResponse(id, HarvesterType.MEMORY_USAGE);
        harvestCommandResponse.addData("total", parts[1]);
        harvestCommandResponse.addData("used", parts[2]);
        harvestCommandResponse.addData("free", parts[3]);
        return harvestCommandResponse;
    }


}
