package com.edusalguero.rexoubapp.domain.service.executor.command;


import com.edusalguero.rexoubapp.domain.model.monitor.observer.Observation;
import com.edusalguero.rexoubapp.domain.model.monitor.observer.ObserverType;
import com.edusalguero.rexoubapp.domain.service.executor.command.response.ObserverCommandResponse;
import com.edusalguero.rexoubapp.domain.shared.CheckStatus;
import com.edusalguero.rexoubapp.domain.shared.UniqueId;

public class ServiceStatusCommand implements CommandInterface {

    private final UniqueId id;
    private String serviceName;

    public ServiceStatusCommand(UniqueId id, String serviceName) {
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
        ObserverCommandResponse observerCommandResponse = new ObserverCommandResponse(id, ObserverType.SERVICE, serviceName);

        if (result.contains("running") && !result.contains("not running")) {
            serviceStatus = CheckStatus.UP;
        }
        observerCommandResponse.setData(new Observation(serviceStatus));
        return observerCommandResponse;
    }
}
