package com.edusalguero.rexoubador.domain.service.executor.command;

import com.edusalguero.rexoubador.domain.service.executor.CommandInterface;
import com.edusalguero.rexoubador.domain.service.executor.ExecutionResult;

public class Load implements CommandInterface {

    @Override
    public String getCommandString() {
        return "cat /proc/loadavg";
    }

    @Override
    public ExecutionResult parseResult(String result) {
        String[] parts = result.split(" ");
        ExecutionResult executionResult = new ExecutionResult();
        executionResult.set("type", "load");
        executionResult.set("last", parts[0]);
        executionResult.set("5min", parts[1]);
        executionResult.set("15min", parts[2]);
        return executionResult;
    }


}
