package com.edusalguero.rexoubador.domain.service.executor.command;

import com.edusalguero.rexoubador.domain.service.executor.CommandInterface;
import com.edusalguero.rexoubador.domain.service.executor.ExecutionResult;

public class MemoryUsage implements CommandInterface {

    @Override
    public String getCommandString() {
        return "free -k | grep Mem";
    }

    @Override
    public ExecutionResult parseResult(String result) {
        String[] parts = result.split("\\s+");

        ExecutionResult executionResult = new ExecutionResult();
        executionResult.set("type", "memory_usage");
        executionResult.set("total", parts[1]);
        executionResult.set("used", parts[2]);
        executionResult.set("free", parts[3]);
        return executionResult;
    }


}
