package com.edusalguero.rexoubador.domain.service.executor.command;

import com.edusalguero.rexoubador.domain.model.monitor.harvester.HarvesterType;
import com.edusalguero.rexoubador.domain.service.executor.command.response.HarvestCommandResponse;
import com.edusalguero.rexoubador.domain.shared.UniqueId;

import java.util.HashMap;

public class DiskUsage implements CommandInterface {

    private UniqueId id;

    public DiskUsage(UniqueId id) {
        this.id = id;
    }
    @Override
    public String getCommandString() {
        return "df -mlT | grep '^\\/dev'";
    }

    @Override
    public HarvestCommandResponse parseResult(String result) {
        String[] mountPoints = result.split("\\n");

        HarvestCommandResponse harvestCommandResponse = new HarvestCommandResponse(id,HarvesterType.DISK_USAGE);

        for (String point : mountPoints) {
            String[] parts = point.split("\\s+");
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("filesystem", parts[0]);
            map.put("total", parts[2]);
            map.put("used", parts[3]);
            map.put("free", parts[4]);
            map.put("percentage_of_use", parts[5].replace("%", ""));
            map.put("type", parts[1]);
            map.put("mounted_on", parts[6]);
            harvestCommandResponse.addData(parts[6], map);
        }

        return harvestCommandResponse;
    }


}
