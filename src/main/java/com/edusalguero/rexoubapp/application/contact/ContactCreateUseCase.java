package com.edusalguero.rexoubapp.application.contact;


import com.edusalguero.rexoubapp.domain.model.contact.ContactId;
import com.edusalguero.rexoubapp.domain.model.contact.ContactRepository;
import com.edusalguero.rexoubapp.domain.model.user.User;
import com.edusalguero.rexoubapp.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactCreateUseCase {

    private final ContactRepository contactRepository;
    private final UserRepository userRepository;

    @Autowired
    public ContactCreateUseCase(ContactRepository contactRepository, UserRepository userRepository) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
    }

    public ContactId execute(ContactCreateRequest contactRequest) {
        ContactId contactId = contactRepository.nextIdentity();
        User user = userRepository.ofId(contactRequest.getUserId());
        user.addContact(contactId, contactRequest.getEmail(), contactRequest.getSlackWebhookUrl(), contactRequest.getSlackChannelOrUsername());
        userRepository.update(user);
        return contactId;
    }
}
