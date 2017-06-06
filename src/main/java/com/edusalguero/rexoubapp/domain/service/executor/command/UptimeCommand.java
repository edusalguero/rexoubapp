package com.edusalguero.rexoubapp.domain.service.executor.command;


import com.edusalguero.rexoubapp.domain.model.monitor.harvester.harvest.UptimeHarvest;
import com.edusalguero.rexoubapp.domain.service.executor.command.response.UptimeCommandResponse;

public class UptimeCommand implements CommandInterface {

    @Override
    public String getCommandString() {
        return "cat /proc/uptime";
    }

    @Override
    public UptimeCommandResponse parseResult(String result) {
        String[] parts = result.split(" ");
        Float uptime = Float.parseFloat(parts[0]);
        UptimeCommandResponse uptimeCommandResponse = new UptimeCommandResponse("uptime");
        uptimeCommandResponse.setData(new UptimeHarvest(uptime.intValue()));
        return uptimeCommandResponse;
    }
}
