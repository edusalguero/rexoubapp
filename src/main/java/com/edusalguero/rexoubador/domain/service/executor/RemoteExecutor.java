package com.edusalguero.rexoubador.domain.service.executor;

import com.edusalguero.rexoubador.domain.service.executor.command.CommandInterface;
import com.edusalguero.rexoubador.domain.service.executor.command.response.CommandResponseInterface;

public interface RemoteExecutor {
    CommandResponseInterface execute(Connection connection, CommandInterface command);
}
