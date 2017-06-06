package com.edusalguero.rexoubapp.infrastructure.persistence.jpa;

import com.edusalguero.rexoubapp.domain.model.monitor.observer.Observer;
import com.edusalguero.rexoubapp.domain.model.monitor.observer.ObserverId;
import com.edusalguero.rexoubapp.domain.model.monitor.observer.ObserverNotFoundException;
import com.edusalguero.rexoubapp.domain.model.monitor.observer.ObserverRepository;
import com.edusalguero.rexoubapp.domain.model.user.User;
import com.edusalguero.rexoubapp.domain.shared.Status;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class ObserverRepositoryJPA extends JPARepository implements ObserverRepository {
    @Override
    public Observer ofId(ObserverId observerId) {
        try {
            String hql = "FROM Observer as observer where observer.id = :observerId and observer.status<>:statusDeleted";
            return (Observer) entityManager.createQuery(hql).
                    setParameter("observerId", observerId).
                    setParameter("statusDeleted", Status.DELETED).
                    getSingleResult();
        } catch (NoResultException e) {
            throw new ObserverNotFoundException();
        }
    }

    @Override
    public Collection<Observer> ofUser(User user) {
        String hql = "FROM Observer as observer where observer.user = :user and observer.status<>:statusDeleted";
        return (List<Observer>) entityManager.createQuery(hql).
                setParameter("user", user).
                setParameter("statusDeleted", Status.DELETED).
                getResultList();
    }

    @Override
    public ObserverId nextIdentity() {
        final String random = UUID.randomUUID().toString().toUpperCase();
        return new ObserverId(random);
    }

    @Override
    public void update(Observer observer) {
        entityManager.merge(observer);
        entityManager.flush();
    }
}
