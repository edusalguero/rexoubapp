package com.edusalguero.rexoubador.domain.user;


import com.edusalguero.rexoubador.domain.Status;
import com.edusalguero.rexoubador.domain.contact.Contact;
import com.edusalguero.rexoubador.domain.contact.ContactId;
import com.edusalguero.rexoubador.domain.contact.ContactNotFoundException;
import com.edusalguero.rexoubador.domain.user.service.HashingService;
import com.edusalguero.rexoubador.infraestructure.service.BcryptHashingService;

import javax.persistence.*;
import java.util.*;

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
        return contacts;
    }

    public Boolean addContact(ContactId contactId, String email, String slackWebhookUrl, String slackChannelOrUsername) {
        Contact contact = new Contact(this, contactId, email, slackWebhookUrl, slackChannelOrUsername);
        return contacts.add(contact);
    }

    public void deleteContact(ContactId contactId) {
        int index = 0;
        Boolean deleted = false;
        System.out.println(contacts);
        for (Contact contact : contacts) {

            if (contact.id().equals(contactId.getId())) {
                contacts.remove(index);
                deleted = true;
                break;
            }
            index++;
        }
        System.out.println(contacts);
        if (!deleted) {
            throw new ContactNotFoundException();
        }
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
