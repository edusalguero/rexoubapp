package com.edusalguero.rexoubapp.domain.service.executor.command;

import com.edusalguero.rexoubapp.domain.model.monitor.harvester.HarvesterType;
import com.edusalguero.rexoubapp.domain.model.monitor.harvester.harvest.MemoryUsageHarvest;
import com.edusalguero.rexoubapp.domain.service.executor.command.response.HarvestCommandResponse;
import com.edusalguero.rexoubapp.domain.shared.UniqueId;

public class MemoryUsageCommand implements CommandInterface {

    private UniqueId id;

    public MemoryUsageCommand(UniqueId id) {
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
        harvestCommandResponse.setData(new MemoryUsageHarvest(Integer.valueOf(parts[1]), Integer.valueOf(parts[2]), Integer.valueOf(parts[3])));
        return harvestCommandResponse;
    }


}
