package com.edusalguero.rexoubador.application.contact;

import com.edusalguero.rexoubador.domain.model.contact.ContactId;
import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserId;
import com.edusalguero.rexoubador.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactDeleteUseCase {

    private final UserRepository userRepository;

    @Autowired
    public ContactDeleteUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(ContactId contactId, UserId userId) {
        User user = userRepository.ofId(userId);
        user.deleteContact(contactId);
        userRepository.update(user);
    }
}
