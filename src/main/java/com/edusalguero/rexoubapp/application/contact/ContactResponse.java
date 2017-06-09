package com.edusalguero.rexoubapp.application.contact;

import com.edusalguero.rexoubapp.application.datatransformer.DateConverter;
import com.edusalguero.rexoubapp.domain.model.contact.Contact;
import com.edusalguero.rexoubapp.domain.model.contact.Slack;
import com.edusalguero.rexoubapp.domain.shared.Status;

import java.util.Date;


public class ContactResponse {
    private String id;
    private String email;
    private Date entryDate;
    private Status status;
    private Slack slack;

    ContactResponse(Contact contact) {
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
}
