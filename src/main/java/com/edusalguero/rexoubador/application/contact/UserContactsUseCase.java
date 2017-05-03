package com.edusalguero.rexoubador.application.contact;

import com.edusalguero.rexoubador.domain.contact.Contact;
import com.edusalguero.rexoubador.domain.user.User;
import com.edusalguero.rexoubador.domain.user.UserId;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserContactsUseCase {
    @Autowired
    private UserRepositoryJPA userRepository;

    public ArrayList<ContactResponse> execute(UserId userId) {
        ArrayList<ContactResponse> userContacts = new ArrayList<>();
        User user = userRepository.ofId(userId);
        List<Contact> contacts = user.contacts();
        for (Contact contact : contacts) {
            userContacts.add(new ContactResponse(contact));
        }
        return userContacts;
    }
}
