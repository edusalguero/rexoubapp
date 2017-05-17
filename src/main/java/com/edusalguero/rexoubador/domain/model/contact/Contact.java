package com.edusalguero.rexoubador.domain.model.contact;

import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.shared.Status;

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

    @Override
    public String toString() {
        return "Contact{" +
                "user=" + user +
                ", contactId=" + contactId +
                ", email='" + email + '\'' +
                ", slackWebhookUrl='" + slackWebhookUrl + '\'' +
                ", slackChannelOrUsername='" + slackChannelOrUsername + '\'' +
                ", entryDate=" + entryDate +
                ", status=" + status +
                '}';
    }

    @Column(name = "slack_webhook_url")
    private String slackWebhookUrl = null;

    @Column(name = "slack_channel_or_username")
    private String slackChannelOrUsername = null;

    @Column(name = "entry_date", columnDefinition = "DATETIME", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date entryDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Basic(optional = false)
    @Column(name = "updated_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public User user() {
        return user;
    }

    public Contact(User user, ContactId contactId, String email) {
        this.user = user;
        this.contactId = contactId;
        this.email = email;
        this.entryDate = new Date();
        this.status = Status.ENABLED;
    }

    public Contact(User user, ContactId contactId, String slackWebhookUrl, String slackChannelOrUsername) {
        this.user = user;
        this.contactId = contactId;
        this.slackWebhookUrl = slackWebhookUrl;
        this.slackChannelOrUsername = slackChannelOrUsername;
        this.entryDate = new Date();
        this.status = Status.ENABLED;
    }

    public Contact(User user, ContactId contactId, String email, String slackWebhookUrl, String slackChannelOrUsername) {
        this.user = user;
        this.contactId = contactId;
        this.email = email;
        this.slackWebhookUrl = slackWebhookUrl;
        this.slackChannelOrUsername = slackChannelOrUsername;
        this.entryDate = new Date();
        this.status = Status.ENABLED;
    }


    protected Contact() {
        // Needed by JPA
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

    public String slackWebhookUrl() {
        return slackWebhookUrl;
    }

    public String slackChannelOrUsername() {
        return slackChannelOrUsername;
    }

    public void email(String email) {
        this.email = email;
    }

    public void slackWebhookUrl(String slackWebhookUrl) {
        this.slackWebhookUrl = slackWebhookUrl;
    }

    public void slackChannelOrUsername(String slackChannelOrUsername) {
        this.slackChannelOrUsername = slackChannelOrUsername;
    }

    public Boolean hasEmail()
    {
        return !email.isEmpty();
    }

    public Boolean hasSlack()
    {
        return !slackChannelOrUsername.isEmpty() && !slackWebhookUrl.isEmpty();
    }

    public void updateStatus(Status status) {
        this.status = status;
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
}
