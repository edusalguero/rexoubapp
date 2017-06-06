package com.edusalguero.rexoubapp.infrastructure.service;

import com.edusalguero.rexoubapp.domain.model.monitor.harvester.HarvesterCommandFactory;
import com.edusalguero.rexoubapp.domain.model.monitor.observer.ObserverCommandFactory;
import com.edusalguero.rexoubapp.domain.model.server.Server;
import com.edusalguero.rexoubapp.domain.model.server.harvester.ServerHarvester;
import com.edusalguero.rexoubapp.domain.model.server.observer.ServerObserver;
import com.edusalguero.rexoubapp.domain.service.ServerMonitorsExecutorService;
import com.edusalguero.rexoubapp.domain.service.executor.Connection;
import com.edusalguero.rexoubapp.domain.service.executor.RemoteExecutor;
import com.edusalguero.rexoubapp.domain.service.executor.command.UptimeCommand;
import com.edusalguero.rexoubapp.domain.service.executor.command.response.CommandResponseInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class SSHServerMonitorsExecutorService implements ServerMonitorsExecutorService {

    private final RemoteExecutor remoteExecutor;

    @Autowired
    public SSHServerMonitorsExecutorService(RemoteExecutor remoteExecutor) {
        this.remoteExecutor = remoteExecutor;
    }

    @Override
    public Collection<CommandResponseInterface> collect(Server server) {

        Collection<CommandResponseInterface> collectedData = new ArrayList<>();
        Connection connection = new Connection("root", server.ip());
        HarvesterCommandFactory harvesterHarvesterCommandFactory = new HarvesterCommandFactory();
        ObserverCommandFactory observerHarvesterCommandFactory = new ObserverCommandFactory();

        for (ServerHarvester serverHarvester : server.harvesters()) {
            collectedData.add(remoteExecutor.execute(connection, harvesterHarvesterCommandFactory.make(serverHarvester)));
        }

        for (ServerObserver serverObserver : server.observers()) {
            collectedData.add(remoteExecutor.execute(connection, observerHarvesterCommandFactory.make(serverObserver)));
        }

        collectedData.add(remoteExecutor.execute(connection, new UptimeCommand()));

        return collectedData;
    }
}
