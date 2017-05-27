package com.edusalguero.rexoubador.infraestructure.persistence.jpa;

import com.edusalguero.rexoubador.domain.model.event.Event;
import com.edusalguero.rexoubador.domain.model.event.EventId;
import com.edusalguero.rexoubador.domain.model.event.EventRepository;
import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.shared.Status;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class EventRepositoryJPA extends JPARepository implements EventRepository {
    @Override
    public Collection<Event> ofUserInDays(User user, int days) {

        Date date = new Date();
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime minLocalDate = localDateTime.plusDays(-days);
        Date minDate = java.sql.Date.valueOf(minLocalDate.toLocalDate());
        String hql = "FROM Event as event  WHERE event.server.user = :user AND event.server.status<>:status AND date > :minDate";
        Query query = entityManager.createQuery(hql).
                setParameter("user", user).
                setParameter("status", Status.DELETED).
                setParameter("minDate", minDate);
        return (List<Event>) query.getResultList();
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
