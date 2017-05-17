package com.edusalguero.rexoubador.domain.service.executor.command;

import com.edusalguero.rexoubador.domain.model.monitor.harvester.HarvesterType;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.harvest.LoadHarvest;
import com.edusalguero.rexoubador.domain.service.executor.command.response.HarvestCommandResponse;
import com.edusalguero.rexoubador.domain.shared.UniqueId;

public class LoadCommand implements CommandInterface {

    private UniqueId id;

    public LoadCommand(UniqueId id) {
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
        LoadHarvest data = new LoadHarvest(Float.parseFloat(parts[0]), Float.parseFloat(parts[1]), Float.parseFloat(parts[2]));
        harvestCommandResponse.setData(data);
        return harvestCommandResponse;
    }


}
