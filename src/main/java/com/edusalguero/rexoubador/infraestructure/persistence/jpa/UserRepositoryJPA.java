package com.edusalguero.rexoubador.infraestructure.persistence.jpa;


import com.edusalguero.rexoubador.domain.user.User;
import com.edusalguero.rexoubador.domain.user.UserId;
import com.edusalguero.rexoubador.domain.user.UserNotFoundException;
import com.edusalguero.rexoubador.domain.user.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.UUID;


@Repository
@Transactional
public class UserRepositoryJPA extends JPARepository implements UserRepository {

    @Override
    public User ofId(UserId userId) {
        try {
            String hql = "FROM User as user where user.id = :userId";
            return (User) entityManager.createQuery(hql).
                    setParameter("userId", userId).getSingleResult();
        } catch (NoResultException e) {
            throw new UserNotFoundException();
        }
    }

    public User ofUsername(String username) {
        try {
            String hql = "FROM User as user where user.username = :username";
            return (User) entityManager.createQuery(hql).
                    setParameter("username", username).
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
