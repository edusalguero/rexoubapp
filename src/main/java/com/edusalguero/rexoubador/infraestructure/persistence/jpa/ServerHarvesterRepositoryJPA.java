package com.edusalguero.rexoubador.infraestructure.persistence.jpa;

import com.edusalguero.rexoubador.domain.server.Server;
import com.edusalguero.rexoubador.domain.server.harvester.ServerHarvester;
import com.edusalguero.rexoubador.domain.server.harvester.ServerHarvesterId;
import com.edusalguero.rexoubador.domain.server.harvester.ServerHarvesterRepository;
import com.edusalguero.rexoubador.domain.server.observer.ServerObserverNotFoundException;
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
