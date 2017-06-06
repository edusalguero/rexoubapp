package com.edusalguero.rexoubapp.infrastructure.persistence.jpa;

import com.edusalguero.rexoubapp.domain.model.server.Server;
import com.edusalguero.rexoubapp.domain.model.server.harvester.ServerHarvester;
import com.edusalguero.rexoubapp.domain.model.server.harvester.ServerHarvesterId;
import com.edusalguero.rexoubapp.domain.model.server.harvester.ServerHarvesterRepository;
import com.edusalguero.rexoubapp.domain.model.server.observer.ServerObserverNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class ServerHarvesterRepositoryJPA extends JPARepository implements ServerHarvesterRepository {
    @Override
    public ServerHarvester ofId(ServerHarvesterId serverHarvesterId) {
        try {
            String hql = "FROM ServerHarvester as harvester where harvester.id = :serverHarvesterId";
            return (ServerHarvester) entityManager.createQuery(hql).
                    setParameter("serverHarvesterId", serverHarvesterId).
                    getSingleResult();
        } catch (NoResultException e) {
            throw new ServerObserverNotFoundException();
        }
    }

    @Override
    public Collection<ServerHarvester> ofServer(Server server) {
        String hql = "FROM ServerHarvester as harvester where harvester.server = :server";
        return (List<ServerHarvester>) entityManager.createQuery(hql).
                setParameter("server", server).
                getResultList();
    }

    @Override
    public ServerHarvesterId nextIdentity() {
        final String random = UUID.randomUUID().toString().toUpperCase();
        return new ServerHarvesterId(random);
    }

}
