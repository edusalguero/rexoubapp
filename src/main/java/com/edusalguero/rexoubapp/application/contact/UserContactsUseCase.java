package com.edusalguero.rexoubapp.application.contact;

import com.edusalguero.rexoubapp.domain.model.contact.Contact;
import com.edusalguero.rexoubapp.domain.model.user.User;
import com.edusalguero.rexoubapp.domain.model.user.UserId;
import com.edusalguero.rexoubapp.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserContactsUseCase {
    private final UserRepository userRepository;

    @Autowired
    public UserContactsUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
