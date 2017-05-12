package com.edusalguero.rexoubador.domain.service.executor.command.response;


import java.util.HashMap;


public class UptimeCommandResponse extends CommandResponse {


    public UptimeCommandResponse(String name) {
        result.put("type", "uptime");
        result.put("name", name);
        result.put("data", new HashMap<String, Object>());
    }

    @Override
    public String getName() {
        return result.get("name").toString();
    }

    public String getId() {
        return (String) result.get("id");
    }


}
