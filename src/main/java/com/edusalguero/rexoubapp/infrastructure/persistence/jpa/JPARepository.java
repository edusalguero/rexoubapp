package com.edusalguero.rexoubapp.infrastructure.persistence.jpa;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
abstract public class JPARepository {

    @PersistenceContext
    protected EntityManager entityManager;
}
