package com.edusalguero.rexoubador.infraestructure.persistence.jpa;

import com.edusalguero.rexoubador.domain.model.server.Server;
import com.edusalguero.rexoubador.domain.model.server.ServerId;
import com.edusalguero.rexoubador.domain.model.server.ServerNotFoundException;
import com.edusalguero.rexoubador.domain.model.server.ServerRepository;
import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.shared.HarvestStatus;
import com.edusalguero.rexoubador.domain.shared.Status;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Repository
@Transactional
public class ServerRepositoryJPA extends JPARepository implements ServerRepository {

    @Override
    public Server ofId(ServerId serverId) {
        try {
            String hql = "FROM Server as server where server.id = :serverId and server.status<>:statusDeleted";
            return (Server) entityManager.createQuery(hql).
                    setParameter("serverId", serverId).
                    setParameter("statusDeleted", Status.DELETED).
                    getSingleResult();
        } catch (NoResultException e) {
            throw new ServerNotFoundException();
        }
    }

    @Override
    public Collection<Server> ofUser(User user) {
        String hql = "FROM Server as server where server.user = :user and server.status<>:statusDeleted";
        return (List<Server>) entityManager.createQuery(hql).
                setParameter("user", user).
                setParameter("statusDeleted", Status.DELETED).
                getResultList();
    }

    @Override
    public ServerId nextIdentity() {
        final String random = UUID.randomUUID().toString().toUpperCase();
        return new ServerId(random);
    }

    @Override
    public Collection<Server> toBeCollected() {
        List statusToBeCollected = new ArrayList<>();
        statusToBeCollected.add(HarvestStatus.CONNECTION_ERROR);
        statusToBeCollected.add(HarvestStatus.PENDING);
        statusToBeCollected.add(HarvestStatus.DONE);

        Date oneMinuteAgo = new Date(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(1));

        String hql = "FROM Server as server WHERE server.status<>:statusDeleted AND server.harvestStatus IN :statusToBeCollected" +
                " AND ( server.lastHarvestDate < :lastHarvestDate OR server.lastHarvestDate IS NULL)";
        return (List<Server>) entityManager.createQuery(hql).
                setParameter("statusToBeCollected", statusToBeCollected).
                setParameter("statusDeleted", Status.DELETED).
                setParameter("lastHarvestDate", oneMinuteAgo).
                getResultList();
    }

    @Override
    public void update(Server server) {
        entityManager.merge(server);
        entityManager.flush();
    }
}
