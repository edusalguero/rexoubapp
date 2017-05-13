package com.edusalguero.rexoubador.domain.service;

import com.edusalguero.rexoubador.domain.model.server.Server;
import com.edusalguero.rexoubador.domain.service.executor.command.response.CommandResponseInterface;

import java.util.Collection;

public interface ServerMonitorsExecutorService {
    Collection<CommandResponseInterface> collect(Server server);
}
