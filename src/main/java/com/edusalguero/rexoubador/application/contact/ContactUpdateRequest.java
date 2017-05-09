package com.edusalguero.rexoubador.application.contact;

import com.edusalguero.rexoubador.domain.model.contact.ContactId;
import com.edusalguero.rexoubador.domain.model.user.UserId;
import com.edusalguero.rexoubador.domain.shared.Status;

public class ContactUpdateRequest {
    private ContactId contactId;
    private UserId userId;
    private String email;
    private String slackWebhookUrl;
    private String slackChannelOrUsername;
    private Status status;

    public ContactUpdateRequest(UserId userId, ContactId contactId, String email, String slackWebhookUrl, String slackChannelOrUsername, Status status) {
        this.userId = userId;
        this.contactId = contactId;
        this.email = email;
        this.slackWebhookUrl = slackWebhookUrl;
        this.slackChannelOrUsername = slackChannelOrUsername;
        this.status = status;
    }

    public ContactId getContactId() {
        return contactId;
    }

    public UserId getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getSlackWebhookUrl() {
        return slackWebhookUrl;
    }

    public String getSlackChannelOrUsername() {
        return slackChannelOrUsername;
    }

    public Status getStatus() {
        return status;
    }
}
