package com.edusalguero.rexoubapp.domain.service;

import com.edusalguero.rexoubapp.domain.model.server.Server;
import com.edusalguero.rexoubapp.domain.service.executor.command.response.CommandResponseInterface;

import java.util.Collection;

public interface ServerMonitorsExecutorService {
    Collection<CommandResponseInterface> collect(Server server);
}
