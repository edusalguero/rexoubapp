package com.edusalguero.rexoubador.domain.service.executor.command;


import com.edusalguero.rexoubador.domain.model.monitor.observer.ObserverType;
import com.edusalguero.rexoubador.domain.service.executor.command.response.ObserverCommandResponse;
import com.edusalguero.rexoubador.domain.shared.CheckStatus;
import com.edusalguero.rexoubador.domain.shared.UniqueId;

public class ServiceStatus implements CommandInterface {

    private final UniqueId id;
    private String serviceName;

    public ServiceStatus(UniqueId id, String serviceName) {
        this.id = id;
        this.serviceName = serviceName;
    }

    @Override
    public String getCommandString() {
        return "service " + serviceName + " status";
    }

    @Override
    public ObserverCommandResponse parseResult(String result) {
        CheckStatus serviceStatus = CheckStatus.DOWN;
        ObserverCommandResponse observerCommandResponse = new ObserverCommandResponse(id, ObserverType.SERVICE);

        if (result.contains("running")) {
            serviceStatus = CheckStatus.UP;
        }
        observerCommandResponse.addData("name", serviceName);
        observerCommandResponse.addData("status", serviceStatus);
        return observerCommandResponse;
    }
}
