package com.edusalguero.rexoubador.infraestructure.service;

import com.edusalguero.rexoubador.domain.model.monitor.harvester.HarvesterCommandFactory;
import com.edusalguero.rexoubador.domain.model.monitor.observer.ObserverCommandFactory;
import com.edusalguero.rexoubador.domain.model.server.Server;
import com.edusalguero.rexoubador.domain.model.server.harvester.ServerHarvester;
import com.edusalguero.rexoubador.domain.model.server.observer.ServerObserver;
import com.edusalguero.rexoubador.domain.service.ServerMonitorsExecutorService;
import com.edusalguero.rexoubador.domain.service.executor.Connection;
import com.edusalguero.rexoubador.domain.service.executor.RemoteExecutor;
import com.edusalguero.rexoubador.domain.service.executor.command.Uptime;
import com.edusalguero.rexoubador.domain.service.executor.command.response.CommandResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class SSHServerMonitorsExecutorService implements ServerMonitorsExecutorService {

    @Autowired
    private RemoteExecutor remoteExecutor;

    @Override
    public Collection<CommandResponse> collect(Server server) {

        Collection<CommandResponse> collectedData = new ArrayList<>();
        Connection connection = new Connection("root", server.ip());
        HarvesterCommandFactory harvesterHarvesterCommandFactory = new HarvesterCommandFactory();
        ObserverCommandFactory observerHarvesterCommandFactory = new ObserverCommandFactory();

        for (ServerHarvester serverHarvester : server.harvesters()) {
            collectedData.add(remoteExecutor.execute(connection, harvesterHarvesterCommandFactory.make(serverHarvester)));
        }

        for (ServerObserver serverObserver : server.observers()) {
            collectedData.add(remoteExecutor.execute(connection, observerHarvesterCommandFactory.make(serverObserver)));
        }

        collectedData.add(remoteExecutor.execute(connection, new Uptime()));

        return collectedData;
    }
}
