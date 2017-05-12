package com.edusalguero.rexoubador.domain.model.user;


import com.edusalguero.rexoubador.domain.model.contact.Contact;
import com.edusalguero.rexoubador.domain.model.contact.ContactId;
import com.edusalguero.rexoubador.domain.model.contact.ContactNotFoundException;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.*;
import com.edusalguero.rexoubador.domain.model.monitor.observer.*;
import com.edusalguero.rexoubador.domain.model.server.Server;
import com.edusalguero.rexoubador.domain.model.server.ServerId;
import com.edusalguero.rexoubador.domain.model.server.ServerNotFoundException;
import com.edusalguero.rexoubador.domain.model.user.service.HashingService;
import com.edusalguero.rexoubador.domain.shared.Status;
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", targetEntity = Observer.class, orphanRemoval = true)
    private List<Observer> observers = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", targetEntity = Harvester.class, orphanRemoval = true)
    private List<Harvester> harvesters = new ArrayList<>();

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
        Contact contact = contact(contactId);
        contact.delete();
    }

    public Contact contact(ContactId contactId) {
        Contact contact = contacts().stream()
                .filter(c -> c.contactId().equals(contactId))
                .findAny()
                .orElse(null);
        if (contact == null) {
            throw new ContactNotFoundException();
        }
        return contact;
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
        Server server = server(serverId);
        server.delete();
    }

    public Server server(ServerId serverId) {
        Server server = servers().stream()
                .filter(s -> s.serverId().equals(serverId))
                .findAny()
                .orElse(null);
        if (server == null) {
            throw new ServerNotFoundException();
        }
        return server;
    }

    public List<Observer> observers() {
        return observers.stream()
                .filter(o -> !o.isSoftDeleted())
                .collect(Collectors.toList());
    }

    public Boolean addObserver(ObserverId observerId, ObserverType type, String name, String label, Boolean notifyStatusChanges, Boolean notifyInactivity, Status status) {
        ObserverFactory observerFactory = new ObserverFactory();
        Observer observer = observerFactory.make(this, observerId, type, name, label, notifyStatusChanges, notifyInactivity, status);
        return observers.add(observer);
    }

    public void deleteObserver(ObserverId observerId) {
        Observer observer = observer(observerId);
        observer.delete();
    }

    public Observer observer(ObserverId observerId)
    {
        Observer observer = observers().stream()
                .filter(o -> o.observerId().equals(observerId))
                .findAny()
                .orElse(null);
        if (observer == null) {
            throw new ObserverNotFoundException();
        }
        return observer;
    }

    public List<Harvester> harvesters() {
        return harvesters.stream()
                .filter(h -> !h.isSoftDeleted())
                .collect(Collectors.toList());
    }

    public Boolean addHarvester(HarvesterId harvesterId, HarvesterType type, String label, Boolean notifyWarning, Boolean notifyAlert, String warningValue, String alertValue, Status status) {
        HarvesterFactory harvesterFactory = new HarvesterFactory();
        Harvester harvester = harvesterFactory.make(this,  harvesterId,  type,  label,  notifyWarning,  notifyAlert,  warningValue,  alertValue, status);
        return harvesters.add(harvester);
    }

    public void deleteHarvester(HarvesterId harvesterId) {
        Harvester harvester = harvester(harvesterId);
        harvester.delete();
    }

    public Harvester harvester(HarvesterId harvesterId)
    {
        Harvester harvester = harvesters().stream()
                .filter(h -> h.harvesterId().equals(harvesterId))
                .findAny()
                .orElse(null);
        if (harvester == null) {
            throw new HarvesterNotFoundException();
        }
        return harvester;
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

    public UserId userId()
    {
        return  userId;
    }
}
