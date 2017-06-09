package com.edusalguero.rexoubapp.application.event;


import com.edusalguero.rexoubapp.application.datatransformer.DateConverter;
import com.edusalguero.rexoubapp.application.server.ServerIdentificationResponse;
import com.edusalguero.rexoubapp.domain.model.contact.Contact;
import com.edusalguero.rexoubapp.domain.model.event.Event;
import com.edusalguero.rexoubapp.domain.model.server.Server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

public class EventResponse {
    private Collection<Contact> contacts;
    private ServerIdentificationResponse server;
    private String message;
    private Date eventDate;

    public EventResponse(Event event) {
        this.eventDate = event.date();
        this.message = event.message();
        this.contacts = event.contacts();
        this.server = new ServerIdentificationResponse(event.server());

    }

    public String getEventDate() {
        return DateConverter.getFormattedDateOrEmptyString(eventDate);
    }

    public ServerIdentificationResponse getServer() {
        return server;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<EventRecipient> getRecipients() {
        ArrayList<EventRecipient> contactsResponse = new ArrayList<>();
        for (Contact contact : contacts) {
            contactsResponse.add(new EventRecipient(contact));
        }
        return contactsResponse;
    }



}
