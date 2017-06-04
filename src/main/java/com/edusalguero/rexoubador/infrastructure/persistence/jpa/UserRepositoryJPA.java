package com.edusalguero.rexoubador.infrastructure.persistence.jpa;


import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserId;
import com.edusalguero.rexoubador.domain.model.user.UserNotFoundException;
import com.edusalguero.rexoubador.domain.model.user.UserRepository;
import com.edusalguero.rexoubador.domain.shared.Status;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.UUID;


@Repository
@Transactional
public class UserRepositoryJPA extends JPARepository implements UserRepository {

    @Override
    public User ofId(UserId userId) {
        try {
            String hql = "FROM User as user where user.id = :userId and user.status<>:statusDeleted";
            return (User) entityManager.createQuery(hql).
                    setParameter("userId", userId).
                    setParameter("statusDeleted", Status.DELETED).
                    getSingleResult();
        } catch (NoResultException e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    public BigInteger countOfUsername(String username) {
        Query query = entityManager.createNativeQuery("SELECT COUNT(*) FROM user WHERE username = :username " ).
        setParameter("username", username);
        return  (BigInteger) query.getSingleResult();
    }

    public User ofUsername(String username) {
        try {
            String hql = "FROM User as user where user.username = :username and user.status<>:statusDeleted";
            return (User) entityManager.createQuery(hql).
                    setParameter("username", username).
                    setParameter("statusDeleted", Status.DELETED).
                    getSingleResult();
        } catch (NoResultException e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    public void register(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
        entityManager.flush();
    }

    @Override
    public UserId nextIdentity() {
        final String random = UUID.randomUUID().toString().toUpperCase();
        return new UserId(random);
    }

}
