package com.edusalguero.rexoubador.domain.model.contact;

import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.shared.Status;
import com.edusalguero.rexoubador.domain.shared.ValidationException;
import com.edusalguero.rexoubador.domain.shared.validator.EmailValidator;
import com.edusalguero.rexoubador.domain.shared.validator.SlackWebhookUrlValidator;

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
        setSlackWebhookUrl(slackWebhookUrl);
        this.slackChannelOrUsername = slackChannelOrUsername;
        this.entryDate = new Date();
        this.status = Status.ENABLED;
    }

    public Contact(User user, ContactId contactId, String email, String slackWebhookUrl, String slackChannelOrUsername) {
        this.user = user;
        this.contactId = contactId;
        validateData(email, slackWebhookUrl, slackChannelOrUsername);
        setEmail(email);
        setSlackWebhookUrl(slackWebhookUrl);
        this.slackChannelOrUsername = slackChannelOrUsername;
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

    private void setSlackWebhookUrl(String slackWebhookUrl) {
        SlackWebhookUrlValidator slackWebhookUrlValidator = new SlackWebhookUrlValidator();
        if (!slackWebhookUrl.isEmpty() && !slackWebhookUrlValidator.validate(slackWebhookUrl)) {
            throw new ValidationException(String.format("%s is not a valid Slack Webhook URL", slackWebhookUrl));
        }
        this.slackWebhookUrl = slackWebhookUrl;
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
        setEmail(email);
    }

    private void setSlackChannelOrUsername(String slackChannelOrUsername) {
        this.slackChannelOrUsername = slackChannelOrUsername;
    }

    public Boolean hasEmail() {
        return !email.isEmpty();
    }

    public Boolean hasSlack() {
        return !slackChannelOrUsername.isEmpty() && !slackWebhookUrl.isEmpty();
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

        setSlackWebhookUrl(slackWebhookUrl);
        setSlackChannelOrUsername(slackChannelOrUsername);
    }
}
