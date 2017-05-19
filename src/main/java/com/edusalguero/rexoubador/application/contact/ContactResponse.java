package com.edusalguero.rexoubador.application.contact;

import com.edusalguero.rexoubador.application.datatransformer.DateConverter;
import com.edusalguero.rexoubador.domain.model.contact.Contact;
import com.edusalguero.rexoubador.domain.shared.Status;

import java.util.Date;


public class ContactResponse {
    private String id;
    private String email;
    private Date entryDate;
    private Status status;
    private Slack slack;

    public ContactResponse(Contact contact) {
        this.id = contact.id();
        this.email = contact.email();
        this.slack = new Slack(contact.slackChannelOrUsername(), contact.slackWebhookUrl());
        this.entryDate = contact.entryDate();
        this.status = contact.status();
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Slack getSlack() {
        return slack;
    }

    public String getEntryDate() {
        return DateConverter.getFormattedDateOrEmptyString(entryDate);
    }

    public Status getStatus() {
        return status;
    }

    class Slack {
        private String slackWebhookUrl;
        private String slackChannelOrUsername;

        Slack(String slackWebhookUrl, String slackChannelOrUsername) {
            this.slackWebhookUrl = slackWebhookUrl;
            this.slackChannelOrUsername = slackChannelOrUsername;
        }

        public String getSlackWebhookUrl() {
            return slackWebhookUrl;
        }

        public String getSlackChannelOrUsername() {
            return slackChannelOrUsername;
        }
    }
}
