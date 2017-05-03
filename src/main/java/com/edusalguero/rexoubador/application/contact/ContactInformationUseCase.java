package com.edusalguero.rexoubador.application.contact;

import com.edusalguero.rexoubador.domain.contact.Contact;
import com.edusalguero.rexoubador.domain.contact.ContactId;
import com.edusalguero.rexoubador.domain.contact.ContactNotFoundException;
import com.edusalguero.rexoubador.domain.user.UserId;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.ContactRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactInformationUseCase {
    @Autowired
    private ContactRepositoryJPA contactRepository;

    public ContactResponse execute(ContactId contactId, UserId userId) {
        Contact contact = contactRepository.ofId(contactId);
        if (!contact.user().id().equals(userId.getId())) {
            throw new ContactNotFoundException();
        }

        return new ContactResponse(contact);
    }
}
