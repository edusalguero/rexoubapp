package com.edusalguero.rexoubador.infraestructure.persistence.jpa;

import com.edusalguero.rexoubador.domain.server.Server;
import com.edusalguero.rexoubador.domain.server.observer.ServerObserver;
import com.edusalguero.rexoubador.domain.server.observer.ServerObserverId;
import com.edusalguero.rexoubador.domain.server.observer.ServerObserverNotFoundException;
import com.edusalguero.rexoubador.domain.server.observer.ServerObserverRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class ServerObserverRepositoryJPA extends JPARepository implements ServerObserverRepository {
    @Override
    public ServerObserver ofId(ServerObserverId serverObserverId) {
        try {
            String hql = "FROM ServerObserver as observer where observer.id = :serverObserverId";
            return (ServerObserver) entityManager.createQuery(hql).
                    setParameter("serverObserverId", serverObserverId).
                    getSingleResult();
        } catch (NoResultException e) {
            throw new ServerObserverNotFoundException();
        }
    }

    @Override
    public Collection<ServerObserver> ofServer(Server server) {


        String hql = "FROM ServerObserver as observer where observer.server = :server";
        return (List<ServerObserver>) entityManager.createQuery(hql).
                setParameter("server", server).
                getResultList();
    }

    @Override
    public ServerObserverId nextIdentity() {

        final String random = UUID.randomUUID().toString().toUpperCase();
        return new ServerObserverId(random);
    }

}
