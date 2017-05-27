package com.edusalguero.rexoubador.application.event;


import com.edusalguero.rexoubador.application.datatransformer.DateConverter;
import com.edusalguero.rexoubador.domain.model.contact.Contact;
import com.edusalguero.rexoubador.domain.model.event.Event;
import com.edusalguero.rexoubador.domain.model.server.Server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

public class EventResponse {
    private Collection<Contact> contacts;
    private Server server;
    private String message;
    private Date eventDate;

    public EventResponse(Event event) {
        this.eventDate = event.date();
        this.message = event.message();
        this.contacts = event.contacts();
        this.server = event.server();
    }

    public String getEventDate() {
        return DateConverter.getFormattedDateOrEmptyString(eventDate);
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<HashMap<String, String>> getRecipients() {
        ArrayList<HashMap<String, String>> contactsResponse = new ArrayList<>();
        for (Contact contact : contacts) {
            HashMap<String, String> contactHash = new HashMap<>();
            if (contact.email() != null) {
                contactHash.put("email", contact.email());
            }
            if (contact.slackChannelOrUsername() != null) {
                contactHash.put("slack", contact.slackChannelOrUsername());
            }
            contactsResponse.add(contactHash);
        }
        return contactsResponse;
    }

    public HashMap<String, String> getServer() {
        HashMap<String, String> serverMap = new HashMap<>();
        serverMap.put("id", server.serverId().getId());
        serverMap.put("label",server.label());
        return serverMap;
    }

}
