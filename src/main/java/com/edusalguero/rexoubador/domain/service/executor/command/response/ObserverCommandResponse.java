package com.edusalguero.rexoubador.domain.service.executor.command.response;


import com.edusalguero.rexoubador.domain.model.monitor.observer.ObserverType;
import com.edusalguero.rexoubador.domain.shared.UniqueId;

import java.util.HashMap;


public class ObserverCommandResponse extends CommandResponse {

    private final UniqueId id;

    public ObserverCommandResponse(UniqueId uniqueId, ObserverType name) {
        id = uniqueId;
        result.put("type", "observer");
        result.put("name", name);
        result.put("data", new HashMap<String, Object>());
    }

    public UniqueId getId() {
        return id;
    }

    @Override
    public String getName() {
        return (String) getData("name");
    }
}
