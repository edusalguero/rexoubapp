package com.edusalguero.rexoubador.infraestructure.persistence.jpa;

import com.edusalguero.rexoubador.domain.event.Event;
import com.edusalguero.rexoubador.domain.event.EventId;
import com.edusalguero.rexoubador.domain.event.EventRepository;
import com.edusalguero.rexoubador.domain.user.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class EventRepositoryJPA extends JPARepository implements EventRepository {
    @Override
    public Collection<Event> ofUser(User user) {

        String hql = "FROM Event as event  WHERE event.server.user = :user";
        return (List<Event>) entityManager.createQuery(hql).
                setParameter("user", user).
                getResultList();
    }

    @Override
    public EventId nextIdentity() {

        final String random = UUID.randomUUID().toString().toUpperCase();
        return new EventId(random);
    }

    @Override
    public void save(Event event) {
        entityManager.persist(event);
        entityManager.flush();
    }

}
