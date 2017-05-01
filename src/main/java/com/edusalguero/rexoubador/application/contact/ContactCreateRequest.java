package com.edusalguero.rexoubador.application.contact;


import com.edusalguero.rexoubador.domain.Status;
import com.edusalguero.rexoubador.domain.user.UserId;


public class ContactCreateRequest {
    private UserId userId;
    private String email;
    private String slackWebhookUrl;
    private String slackChannelOrUsername;
    private Status status;

    public ContactCreateRequest(UserId userId, String email, String slackWebhookUrl, String slackChannelOrUsername, Status status) {
        this.userId = userId;
        this.email = email;
        this.slackWebhookUrl = slackWebhookUrl;
        this.slackChannelOrUsername = slackChannelOrUsername;
        this.status = status;
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
