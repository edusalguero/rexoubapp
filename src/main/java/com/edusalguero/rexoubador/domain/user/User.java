package com.edusalguero.rexoubador.domain.user;


import com.edusalguero.rexoubador.domain.Status;
import com.edusalguero.rexoubador.domain.contact.Contact;
import com.edusalguero.rexoubador.domain.contact.ContactId;
import com.edusalguero.rexoubador.domain.contact.ContactNotFoundException;
import com.edusalguero.rexoubador.domain.server.Server;
import com.edusalguero.rexoubador.domain.server.ServerId;
import com.edusalguero.rexoubador.domain.server.ServerNotFoundException;
import com.edusalguero.rexoubador.domain.user.service.HashingService;
import com.edusalguero.rexoubador.infraestructure.service.BcryptHashingService;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "user")
public class User {

    @Transient
    private HashingService hashingService = new BcryptHashingService();

    @EmbeddedId
    private UserId userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "signup_date", columnDefinition = "DATETIME", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date signUpDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Basic(optional = false)
    @Column(name = "updated_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", targetEntity = Contact.class, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", targetEntity = Server.class, orphanRemoval = true)
    private List<Server> servers = new ArrayList<>();

    public User(UserId userId, String username, String password, String firstName, String lastName) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = Status.ENABLED;
        this.signUpDate = new Date();
        this.changePassword(password);
    }

    protected User() {
        // Needed by JPA
    }

    public List<Contact> contacts() {
        return contacts.stream()
                .filter(c -> !c.isSoftDeleted())
                .collect(Collectors.toList());
    }

    public Boolean addContact(ContactId contactId, String email, String slackWebhookUrl, String slackChannelOrUsername) {
        Contact contact = new Contact(this, contactId, email, slackWebhookUrl, slackChannelOrUsername);
        return contacts.add(contact);
    }

    public void deleteContact(ContactId contactId) {
        Contact contact = contacts().stream()
                .filter(c -> c.contactId().equals(contactId))
                .findAny()
                .orElse(null);
        if (contact == null) {
            throw new ContactNotFoundException();
        }
        contact.delete();
    }

    public List<Server> servers() {
        return servers.stream()
                .filter(s -> !s.isSoftDeleted())
                .collect(Collectors.toList());
    }

    public Boolean addServer(ServerId serverId, String label, String ip, Status status) {
        Server server = new Server(this, serverId, label, ip, status);
        return servers.add(server);
    }

    public void deleteServer(ServerId serverId) {
        Server server = servers().stream()
                .filter(s -> s.serverId().equals(serverId))
                .findAny()
                .orElse(null);
        if (server == null) {
            throw new ServerNotFoundException();
        }
        server.delete();
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public Date signUpDate() {
        return signUpDate;
    }

    public String firstName() {
        return firstName;
    }

    public String username() {
        return username;
    }

    public String lastName() {
        return lastName;
    }

    public void firstName(String firstName) {
        this.firstName = firstName;
    }

    public void lastName(String lastName) {
        this.lastName = lastName;
    }

    public void updateStatus(Status status) {
        this.status = status;
    }

    public void changePassword(String password) {
        this.password = hashingService.hash(password.trim());
    }

    public boolean matchPassword(String password) {
        return hashingService.matches(password, this.password);
    }

    public boolean isEnabled() {

        return this.status == Status.ENABLED;
    }

    public String id() {
        return userId.toString();
    }
}
