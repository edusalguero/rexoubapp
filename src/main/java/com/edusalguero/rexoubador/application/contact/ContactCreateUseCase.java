package com.edusalguero.rexoubador.application.contact;


import com.edusalguero.rexoubador.domain.contact.ContactId;
import com.edusalguero.rexoubador.domain.user.User;
import com.edusalguero.rexoubador.domain.user.UserId;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.ContactRepositoryJPA;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactCreateUseCase {

    @Autowired
    private ContactRepositoryJPA contactRepository;
    @Autowired
    private UserRepositoryJPA userRepository;

    public ContactId execute(ContactCreateRequest contactRequest) {
        ContactId contactId = contactRepository.nextIdentity();
        User user = userRepository.ofId(contactRequest.getUserId());
        user.addContact(contactId, contactRequest.getEmail(), contactRequest.getSlackWebhookUrl(), contactRequest.getSlackChannelOrUsername());
        userRepository.update(user);
        return  contactId;
    }
}
