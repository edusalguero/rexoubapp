package com.edusalguero.rexoubapp.domain.model.contact;

import com.edusalguero.rexoubapp.domain.model.user.User;
import com.edusalguero.rexoubapp.domain.shared.Status;
import com.edusalguero.rexoubapp.domain.shared.ValidationException;
import com.edusalguero.rexoubapp.domain.shared.validator.EmailValidator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "contact")
public class Contact {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @EmbeddedId
    private ContactId contactId;

    @Column(name = "email")
    private String email = null;

    @Embedded
    private Slack slack = null;

    @Column(name = "entry_date", columnDefinition = "DATETIME", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date entryDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Basic(optional = false)
    @Column(name = "updated_at", insertable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Contact(User user, ContactId contactId, String email) {
        this.user = user;
        this.contactId = contactId;
        setEmail(email);
        this.entryDate = new Date();
        this.status = Status.ENABLED;
    }

    public Contact(User user, ContactId contactId, String slackWebhookUrl, String slackChannelOrUsername) {
        this.user = user;
        this.contactId = contactId;
        slack(slackWebhookUrl, slackChannelOrUsername);
        this.entryDate = new Date();
        this.status = Status.ENABLED;
    }

    public Contact(User user, ContactId contactId, String email, String slackWebhookUrl, String slackChannelOrUsername) {
        this.user = user;
        this.contactId = contactId;
        validateData(email, slackWebhookUrl, slackChannelOrUsername);
        setEmail(email);
        slack(slackWebhookUrl, slackChannelOrUsername);
        this.entryDate = new Date();
        this.status = Status.ENABLED;
    }

    protected Contact() {
        // Needed by JPA
    }

    private void validateData(String email, String slackWebhookUrl, String slackChannelOrUsername) {
        if (email.isEmpty() && slackChannelOrUsername.isEmpty() && slackWebhookUrl.isEmpty()) {
            throw new ValidationException("A contact must have and email or Slack configuration");
        }
        if (!slackChannelOrUsername.isEmpty() && slackWebhookUrl.isEmpty() ||
                slackChannelOrUsername.isEmpty() && !slackWebhookUrl.isEmpty()) {
            throw new ValidationException("Incomplete Slack configuration");
        }
    }

    public User user() {
        return user;
    }

    private void setEmail(String email) {
        EmailValidator emailValidator = new EmailValidator();
        if (!email.isEmpty() && !emailValidator.validate(email)) {
            throw new ValidationException(String.format("%s is not a valid email", email));
        }
        this.email = email;
    }


    public ContactId contactId() {
        return contactId;
    }

    public String id() {
        return contactId.toString();
    }

    public String email() {
        return email;
    }

    public Slack slack() {
        return slack;
    }


    public void email(String email) {
        setEmail(email);
    }


    public Boolean hasEmail() {
        return !email.isEmpty();
    }

    public Boolean hasSlack() {
        return !slack.isEmpty();
    }

    public void disable() {
        status = Status.DISABLED;
    }

    public void enable() {
        status = Status.ENABLED;
    }

    public Date entryDate() {
        return entryDate;
    }

    public Status status() {
        return status;
    }

    public Boolean isSoftDeleted() {
        return status == Status.DELETED;
    }

    public void delete() {
        status = Status.DELETED;
    }

    public void slack(String slackWebhookUrl, String slackChannelOrUsername) {

        if (!slackChannelOrUsername.isEmpty() && slackWebhookUrl.isEmpty() ||
                slackChannelOrUsername.isEmpty() && !slackWebhookUrl.isEmpty()) {
            throw new ValidationException("Incomplete Slack configuration");
        }

        slack = new Slack(slackWebhookUrl, slackChannelOrUsername);
    }

    public String slackWebhookUrl()
    {
        return slack.getSlackWebhookUrl();
    }

    public String slackChannelOrUsername()
    {
        return slack.getSlackChannelOrUsername();
    }
}
