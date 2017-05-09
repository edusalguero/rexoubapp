package com.edusalguero.rexoubador.domain.service.executor.command;


import com.edusalguero.rexoubador.domain.service.executor.CommandInterface;
import com.edusalguero.rexoubador.domain.service.executor.ExecutionResult;
import com.edusalguero.rexoubador.domain.shared.CheckStatus;

public class ServiceStatus implements CommandInterface {

    private String serviceName;

    public ServiceStatus(String serviceName) {

        this.serviceName = serviceName;
    }

    @Override
    public String getCommandString() {
        return "service " + serviceName + " status";
    }

    @Override
    public ExecutionResult parseResult(String result) {
        String serviceStatus = CheckStatus.DOWN.toString();
        ExecutionResult executionResult = new ExecutionResult();
        if (result.contains("running")) {
            serviceStatus = CheckStatus.UP.toString();
        }
        executionResult.set("name", serviceName);
        executionResult.set("status", serviceStatus);
        return executionResult;
    }
}
