package com.edusalguero.rexoubador.infraestructure.persistence.jpa;

import com.edusalguero.rexoubador.domain.shared.Status;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.Harvester;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.HarvesterId;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.HarvesterNotFoundException;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.HarvesterRepository;
import com.edusalguero.rexoubador.domain.model.user.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class HarvesterRepositoryJPA extends JPARepository implements HarvesterRepository {
    @Override
    public Harvester ofId(HarvesterId harvesterId) {
        try {
            String hql = "FROM Harvester as harvester where harvester.id = :harvesterId and harvester.status<>:statusDeleted";
            return (Harvester) entityManager.createQuery(hql).
                    setParameter("harvesterId", harvesterId).
                    setParameter("statusDeleted", Status.DELETED).
                    getSingleResult();
        } catch (NoResultException e) {
            throw new HarvesterNotFoundException();
        }
    }

    @Override
    public Collection<Harvester> ofUser(User user) {
        String hql = "FROM Harvester as harvester where harvester.user = :user and harvester.status<>:statusDeleted";
        return (List<Harvester>) entityManager.createQuery(hql).
                setParameter("user", user).
                setParameter("statusDeleted", Status.DELETED).
                getResultList();
    }

    @Override
    public HarvesterId nextIdentity() {
        final String random = UUID.randomUUID().toString().toUpperCase();
        return new HarvesterId(random);
    }

    @Override
    public void update(Harvester Harvester) {
        entityManager.merge(Harvester);
        entityManager.flush();
    }
}
