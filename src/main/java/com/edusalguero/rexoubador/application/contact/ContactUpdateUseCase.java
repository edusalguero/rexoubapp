package com.edusalguero.rexoubador.application.contact;


import com.edusalguero.rexoubador.domain.model.contact.Contact;
import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserRepository;
import com.edusalguero.rexoubador.domain.shared.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactUpdateUseCase {

    private final UserRepository userRepository;

    @Autowired
    public ContactUpdateUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(ContactUpdateRequest contactUpdateRequest) {

        User user = userRepository.ofId(contactUpdateRequest.getUserId());
        Contact contact = user.contact(contactUpdateRequest.getContactId());
        contact.email(contactUpdateRequest.getEmail());
        contact.slack(contactUpdateRequest.getSlackWebhookUrl(),contactUpdateRequest.getSlackChannelOrUsername());
        if(contactUpdateRequest.getStatus() == Status.DISABLED)
        {
            contact.disable();
        }else{
            contact.enable();
        }
        userRepository.update(user);
    }
}
