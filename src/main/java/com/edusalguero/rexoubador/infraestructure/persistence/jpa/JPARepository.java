package com.edusalguero.rexoubador.infraestructure.persistence.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

abstract public class JPARepository {

    @PersistenceContext
    protected EntityManager entityManager;
}
