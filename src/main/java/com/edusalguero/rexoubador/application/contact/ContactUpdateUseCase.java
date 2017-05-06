package com.edusalguero.rexoubador.application.contact;


import com.edusalguero.rexoubador.domain.contact.Contact;
import com.edusalguero.rexoubador.domain.contact.ContactNotFoundException;
import com.edusalguero.rexoubador.domain.user.User;
import com.edusalguero.rexoubador.domain.user.UserRepository;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.ContactRepositoryJPA;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactUpdateUseCase {

    @Autowired
    private UserRepositoryJPA userRepository;

    public void execute(ContactUpdateRequest contactUpdateRequest) {

        User user = userRepository.ofId(contactUpdateRequest.getUserId());
        Contact contact =user.contact(contactUpdateRequest.getContactId());
        contact.email(contactUpdateRequest.getEmail());
        contact.slackChannelOrUsername(contactUpdateRequest.getSlackChannelOrUsername());
        contact.slackWebhookUrl(contactUpdateRequest.getSlackWebhookUrl());
        userRepository.update(user);
    }
}
