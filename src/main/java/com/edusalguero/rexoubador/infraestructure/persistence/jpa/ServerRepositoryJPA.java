package com.edusalguero.rexoubador.infraestructure.persistence.jpa;

import com.edusalguero.rexoubador.domain.contact.ContactNotFoundException;
import com.edusalguero.rexoubador.domain.server.Server;
import com.edusalguero.rexoubador.domain.server.ServerId;
import com.edusalguero.rexoubador.domain.server.ServerRepository;
import com.edusalguero.rexoubador.domain.user.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class ServerRepositoryJPA extends JPARepository implements ServerRepository {

    @Override
    public Server ofId(ServerId serverId) {
        try {
            String hql = "FROM Server as server where server.id = :serverId";
            return (Server) entityManager.createQuery(hql).
                    setParameter("serverId", serverId).getSingleResult();
        } catch (NoResultException e) {
            throw new ContactNotFoundException();
        }
    }

    @Override
    public Collection<Server> ofUser(User user) {
        try {
            String hql = "FROM Server as server where server.user = :user";
            return (List<Server>) entityManager.createQuery(hql).
                    setParameter("user", user).getResultList();
        } catch (NoResultException e) {
            throw new ContactNotFoundException();
        }
    }

    @Override
    public ServerId nextIdentity() {
        final String random = UUID.randomUUID().toString().toUpperCase();
        return new ServerId(random);
    }

    @Override
    public void update(Server server) {
        entityManager.merge(server);
        entityManager.flush();
    }
}
