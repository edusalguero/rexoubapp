package com.edusalguero.rexoubador.domain.service.executor;

import com.edusalguero.rexoubador.domain.service.executor.command.CommandInterface;
import com.edusalguero.rexoubador.domain.service.executor.command.response.CommandResponse;

public interface RemoteExecutor {
    CommandResponse execute(Connection connection, CommandInterface command);
}
