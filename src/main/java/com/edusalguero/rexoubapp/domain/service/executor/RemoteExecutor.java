package com.edusalguero.rexoubapp.domain.service.executor;

import com.edusalguero.rexoubapp.domain.service.executor.command.CommandInterface;
import com.edusalguero.rexoubapp.domain.service.executor.command.response.CommandResponseInterface;

public interface RemoteExecutor {
    CommandResponseInterface execute(Connection connection, CommandInterface command);
}
