package com.edusalguero.rexoubador.infraestructure.persistence.jpa;

import com.edusalguero.rexoubador.domain.Status;
import com.edusalguero.rexoubador.domain.contact.Contact;
import com.edusalguero.rexoubador.domain.contact.ContactId;
import com.edusalguero.rexoubador.domain.contact.ContactNotFoundException;
import com.edusalguero.rexoubador.domain.contact.ContactRepository;
import com.edusalguero.rexoubador.domain.user.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.UUID;


@Repository
@Transactional
public class ContactRepositoryJPA extends JPARepository implements ContactRepository{

    @Override
    public Contact ofId(ContactId contactId) {
        try {
            String hql = "FROM Contact as contact where contact.id = :contactId and contact.status<>:statusDeleted";
            return (Contact) entityManager.createQuery(hql).
                    setParameter("contactId", contactId).
                    setParameter("statusDeleted", Status.DELETED).
                    getSingleResult();
        } catch (NoResultException e) {
            throw new ContactNotFoundException();
        }
    }

    @Override
    public List<Contact> ofUser(User user) {
        try {
            String hql = "FROM Contact as contact where contact.user = :user and contact.status<>:statusDeleted";
            return (List <Contact>) entityManager.createQuery(hql).
                    setParameter("user", user).
                    setParameter("statusDeleted", Status.DELETED).
                    getResultList();
        } catch (NoResultException e) {
            throw new ContactNotFoundException();
        }
    }

    @Override
    public ContactId nextIdentity() {
        final String random = UUID.randomUUID().toString().toUpperCase();
        return new ContactId(random);
    }

    @Override
    public void update(Contact contact) {
        entityManager.merge(contact);
        entityManager.flush();
    }

}
