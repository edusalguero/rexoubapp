package com.edusalguero.rexoubador.domain.service.executor;

public interface RemoteExecutor {
    ExecutionResult execute(Connection connection, CommandInterface command);
}
