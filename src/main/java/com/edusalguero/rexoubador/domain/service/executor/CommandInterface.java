package com.edusalguero.rexoubador.domain.service.executor;


public interface CommandInterface {

    String getCommandString();

    ExecutionResult parseResult(String result);
}
