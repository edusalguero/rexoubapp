package com.edusalguero.rexoubador.domain.model.monitor.harvester;


import com.edusalguero.rexoubador.domain.model.server.harvester.ServerHarvester;
import com.edusalguero.rexoubador.domain.service.executor.command.CommandInterface;
import com.edusalguero.rexoubador.domain.service.executor.command.DiskUsage;
import com.edusalguero.rexoubador.domain.service.executor.command.Load;
import com.edusalguero.rexoubador.domain.service.executor.command.MemoryUsage;

public class HarvesterCommandFactory {

    public CommandInterface make(ServerHarvester serverHarvester) {
        switch (serverHarvester.harvester().type()) {
            case LOAD:
                return new Load(serverHarvester.serverHarvesterId());
            case DISK_USAGE:
                return new DiskUsage(serverHarvester.serverHarvesterId());
            case MEMORY_USAGE:
                return new MemoryUsage(serverHarvester.serverHarvesterId());
            default:
                throw new InvalidHarvesterTypeException();
        }
    }
}
