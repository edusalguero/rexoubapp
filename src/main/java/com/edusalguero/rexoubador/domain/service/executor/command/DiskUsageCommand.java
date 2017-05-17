package com.edusalguero.rexoubador.domain.service.executor.command;

import com.edusalguero.rexoubador.domain.model.monitor.harvester.HarvesterType;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.harvest.DiskMountPoint;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.harvest.DiskUsageHarvest;
import com.edusalguero.rexoubador.domain.service.executor.command.response.HarvestCommandResponse;
import com.edusalguero.rexoubador.domain.shared.UniqueId;

public class DiskUsageCommand implements CommandInterface {

    private UniqueId id;

    public DiskUsageCommand(UniqueId id) {
        this.id = id;
    }

    @Override
    public String getCommandString() {
        return "df -mlT | grep '^\\/dev'";
    }

    @Override
    public HarvestCommandResponse parseResult(String result) {
        String[] mountPoints = result.split("\\n");

        HarvestCommandResponse harvestCommandResponse = new HarvestCommandResponse(id, HarvesterType.DISK_USAGE);

        DiskUsageHarvest diskUsageHarvest = new DiskUsageHarvest();
        for (String point : mountPoints) {
            String[] parts = point.split("\\s+");
            Integer total = Integer.valueOf(parts[2]);
            Integer used = Integer.valueOf(parts[3]);
            Integer free = Integer.valueOf(parts[4]);
            Integer percentageOfUse = Integer.valueOf(parts[5].replace("%", ""));
            String type = parts[1];
            String mountedOn = parts[6];
            String filesystem = parts[0];
            DiskMountPoint diskMountPoint = new DiskMountPoint(total, used, free, percentageOfUse, type, filesystem, mountedOn);
            diskUsageHarvest.addMountPoint(diskMountPoint);
        }
        harvestCommandResponse.setData(diskUsageHarvest);
        return harvestCommandResponse;
    }


}
