package com.edusalguero.rexoubador.application.contact;


import com.edusalguero.rexoubador.domain.model.contact.ContactId;
import com.edusalguero.rexoubador.domain.model.contact.ContactRepository;
import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactCreateUseCase {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private UserRepository userRepository;

    public ContactId execute(ContactCreateRequest contactRequest) {
        ContactId contactId = contactRepository.nextIdentity();
        User user = userRepository.ofId(contactRequest.getUserId());
        user.addContact(contactId, contactRequest.getEmail(), contactRequest.getSlackWebhookUrl(), contactRequest.getSlackChannelOrUsername());
        userRepository.update(user);
        return contactId;
    }
}
