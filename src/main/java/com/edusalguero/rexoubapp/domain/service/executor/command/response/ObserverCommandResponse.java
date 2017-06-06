package com.edusalguero.rexoubapp.domain.service.executor.command.response;


import com.edusalguero.rexoubapp.domain.model.monitor.observer.ObserverType;
import com.edusalguero.rexoubapp.domain.shared.UniqueId;


public class ObserverCommandResponse extends CommandResponse {

    private final UniqueId id;

    public ObserverCommandResponse(UniqueId uniqueId, ObserverType type, String name) {
        id = uniqueId;
        this.type = type.toString();
        this.name = name;
    }

    public UniqueId getId() {
        return id;
    }


    @Override
    public String getName() {
        return (String) name;
    }
}
