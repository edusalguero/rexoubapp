package com.edusalguero.rexoubador.application.contact;

import com.edusalguero.rexoubador.domain.Status;
import com.edusalguero.rexoubador.domain.contact.Contact;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class ContactResponse {
    private String id;
    private String email;

    private String slackWebhookUrl;

    private String slackChannelOrUsername;


    private Date entryDate;

    private Status status;

    ContactResponse(Contact contact) {
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

    public String getSlackWebhookUrl() {
        return slackWebhookUrl;
    }

    public String getSlackChannelOrUsername() {
        return slackChannelOrUsername;
    }

    public String getEntryDate() {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(tz);
        return df.format(entryDate);
    }

    public Status getStatus() {
        return status;
    }
}
