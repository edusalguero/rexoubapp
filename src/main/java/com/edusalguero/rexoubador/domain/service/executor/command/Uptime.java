package com.edusalguero.rexoubador.domain.service.executor.command;


import com.edusalguero.rexoubador.domain.service.executor.command.response.UptimeCommandResponse;

public class Uptime  implements CommandInterface{

    @Override
    public String getCommandString() {
        return "cat /proc/uptime";
    }

    @Override
    public UptimeCommandResponse parseResult(String result) {
        String[] parts = result.split(" ");
        Float uptime = Float.parseFloat(parts[0]);
        UptimeCommandResponse uptimeCommandResponse = new UptimeCommandResponse("uptime");
        uptimeCommandResponse.addData("uptime", uptime.intValue());
        return uptimeCommandResponse;
    }
}
