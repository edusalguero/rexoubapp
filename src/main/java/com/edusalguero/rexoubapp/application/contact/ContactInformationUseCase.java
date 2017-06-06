package com.edusalguero.rexoubapp.application.contact;

import com.edusalguero.rexoubapp.domain.model.contact.Contact;
import com.edusalguero.rexoubapp.domain.model.contact.ContactId;
import com.edusalguero.rexoubapp.domain.model.user.UserId;
import com.edusalguero.rexoubapp.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactInformationUseCase {
    private final UserRepository userRepository;

    @Autowired
    public ContactInformationUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ContactResponse execute(ContactId contactId, UserId userId) {
        Contact contact = userRepository.ofId(userId).contact(contactId);
        return new ContactResponse(contact);
    }
}
