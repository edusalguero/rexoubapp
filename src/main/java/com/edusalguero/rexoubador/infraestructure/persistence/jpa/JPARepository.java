package com.edusalguero.rexoubador.infraestructure.persistence.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

abstract public class JPARepository {

    @PersistenceContext
    protected EntityManager entityManager;
}
