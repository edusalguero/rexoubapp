package com.edusalguero.rexoubador.domain.service.executor.command.response;


import java.util.HashMap;


public class UptimeCommandResponse extends CommandResponse {


    public UptimeCommandResponse(String name) {
        this.type =  "uptime";
        this.name =  name;
    }

    @Override
    public String getName() {
        return (String) name;
    }

}
