package com.edusalguero.rexoubador.application.contact;

import com.edusalguero.rexoubador.domain.contact.Contact;
import com.edusalguero.rexoubador.domain.contact.ContactId;
import com.edusalguero.rexoubador.domain.contact.ContactNotFoundException;
import com.edusalguero.rexoubador.domain.user.UserId;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.ContactRepositoryJPA;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactInformationUseCase {
    @Autowired
    private UserRepositoryJPA userRepository;

    public ContactResponse execute(ContactId contactId, UserId userId) {
        Contact contact = userRepository.ofId(userId).contact(contactId);
        return new ContactResponse(contact);
    }
}
