package com.edusalguero.rexoubador.domain.event;

import com.edusalguero.rexoubador.domain.contact.Contact;
import com.edusalguero.rexoubador.domain.server.Server;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "event")
public class Event {


    @ManyToMany(targetEntity = Contact.class)
    @JoinTable(name = "event_contact")
    private Collection contact;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "server_id")
    private Server server;

    @EmbeddedId
    private EventId eventId;

    @Column(name = "message")
    private String message;
    @Column(name = "date", columnDefinition = "DATETIME", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Event(EventId eventId, Server server, Collection<Contact> contacts, String message) {
        this.eventId = eventId;
        this.contact = contacts;
        this.message = message;
        this.date = new Date();
        this.server = server;
    }

    protected Event() {
        // Needed by JPA
    }

    public String id() {
        return eventId.getId();
    }

    public String message() {
        return message;
    }

    public Date date() {
        return date;
    }

    public Server server() {
        return server;
    }

    public Collection<Contact> contacts() {
        return (Collection<Contact>) contact;
    }


}
