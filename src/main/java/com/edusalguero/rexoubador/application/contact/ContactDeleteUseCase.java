package com.edusalguero.rexoubador.application.contact;

import com.edusalguero.rexoubador.domain.contact.ContactId;
import com.edusalguero.rexoubador.domain.user.User;
import com.edusalguero.rexoubador.domain.user.UserId;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactDeleteUseCase {

    @Autowired
    private UserRepositoryJPA userRepository;

    public void execute(ContactId contactId, UserId userId)
    {
        User user = userRepository.ofId(userId);
        user.deleteContact(contactId);
        userRepository.update(user);
    }
}
