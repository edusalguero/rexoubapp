package com.edusalguero.rexoubador.application.contact;


import com.edusalguero.rexoubador.domain.model.contact.Contact;
import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactUpdateUseCase {

    @Autowired
    private UserRepository userRepository;

    public void execute(ContactUpdateRequest contactUpdateRequest) {

        User user = userRepository.ofId(contactUpdateRequest.getUserId());
        Contact contact = user.contact(contactUpdateRequest.getContactId());
        contact.email(contactUpdateRequest.getEmail());
        contact.slackChannelOrUsername(contactUpdateRequest.getSlackChannelOrUsername());
        contact.slackWebhookUrl(contactUpdateRequest.getSlackWebhookUrl());
        userRepository.update(user);
    }
}
