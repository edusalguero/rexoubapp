package com.edusalguero.rexoubapp.domain.model.monitor.harvester;


import com.edusalguero.rexoubapp.domain.model.server.harvester.ServerHarvester;
import com.edusalguero.rexoubapp.domain.service.executor.command.CommandInterface;
import com.edusalguero.rexoubapp.domain.service.executor.command.DiskUsageCommand;
import com.edusalguero.rexoubapp.domain.service.executor.command.LoadCommand;
import com.edusalguero.rexoubapp.domain.service.executor.command.MemoryUsageCommand;

public class HarvesterCommandFactory {

    public CommandInterface make(ServerHarvester serverHarvester) {
        switch (serverHarvester.harvester().type()) {
            case LOAD:
                return new LoadCommand(serverHarvester.serverHarvesterId());
            case DISK_USAGE:
                return new DiskUsageCommand(serverHarvester.serverHarvesterId());
            case MEMORY_USAGE:
                return new MemoryUsageCommand(serverHarvester.serverHarvesterId());
            default:
                throw new InvalidHarvesterTypeException();
        }
    }
}
