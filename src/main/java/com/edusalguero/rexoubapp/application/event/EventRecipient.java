package com.edusalguero.rexoubapp.application.event;


import com.edusalguero.rexoubapp.domain.model.contact.Contact;

public class EventRecipient {
    private String email;
    private String slack;

    public EventRecipient(Contact contact)
    {
        if (contact.email() != null) {
            email = contact.email();
        }
        if (contact.slackChannelOrUsername() != null) {
            slack = contact.slackChannelOrUsername();

        }
    }

    public String getEmail() {
        return email;
    }

    public String getSlack() {
        return slack;
    }
}
