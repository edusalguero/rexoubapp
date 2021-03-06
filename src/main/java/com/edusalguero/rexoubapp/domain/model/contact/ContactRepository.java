package com.edusalguero.rexoubapp.domain.model.contact;

import com.edusalguero.rexoubapp.domain.model.user.User;

import java.util.Collection;


public interface ContactRepository {

    Contact ofId(ContactId contactId);

    Collection<Contact> ofUser(User user);

    ContactId nextIdentity();

    void update(Contact contact);

}
