package com.edusalguero.rexoubador.application.contact;

import com.edusalguero.rexoubador.application.datatransformer.DateConverter;
import com.edusalguero.rexoubador.domain.Status;
import com.edusalguero.rexoubador.domain.contact.Contact;

import java.util.Date;
import java.util.HashMap;


public class ContactResponse {
    private String id;
    private String email;

    private String slackWebhookUrl;

    private String slackChannelOrUsername;


    private Date entryDate;

    private Status status;

    public ContactResponse(Contact contact) {
        this.id = contact.id();

        this.email = contact.email();
        this.slackChannelOrUsername = contact.slackChannelOrUsername();
        this.slackWebhookUrl = contact.slackWebhookUrl();
        this.entryDate = contact.entryDate();
        this.status = contact.status();
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public HashMap<String, String> getSlack() {
        HashMap<String, String> slack = new HashMap<>();
        slack.put("channelOrUsername", slackChannelOrUsername);
        slack.put("webhookUrl", slackWebhookUrl);
        return slack;
    }


    public String getEntryDate() {
        return DateConverter.getFormattedDateOrEmptyString(entryDate);
    }

    public Status getStatus() {
        return status;
    }
}
