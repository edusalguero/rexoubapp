package com.edusalguero.rexoubador.application.contact;


import com.edusalguero.rexoubador.domain.contact.Contact;
import com.edusalguero.rexoubador.domain.contact.ContactNotFoundException;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.ContactRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactUpdateUseCase {

    @Autowired
    private ContactRepositoryJPA contactRepository;

    public void execute(ContactUpdateRequest contactUpdateRequest) {
        Contact contact = contactRepository.ofId(contactUpdateRequest.getContactId());
        if (!contact.user().id().equals(contactUpdateRequest.getUserId().getId())) {
            throw new ContactNotFoundException();
        }
        contact.email(contactUpdateRequest.getEmail());
        contact.slackChannelOrUsername(contactUpdateRequest.getSlackChannelOrUsername());
        contact.slackWebhookUrl(contactUpdateRequest.getSlackWebhookUrl());
        contactRepository.update(contact);
    }
}
