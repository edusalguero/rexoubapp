package com.edusalguero.rexoubador.domain.service.notification;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class EventMessage implements NotificationMessage{
    private String subject;
    private String body;
    private Date date;

    public EventMessage(String subject, String body, Date date) {
        this.subject = subject;
        this.body = body;
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public String getTextBody() {
        return subject + System.lineSeparator() + " - Occurred on: " + dateString() + System.lineSeparator() + " - Message: " + body;

    }

    public String getMarkdownBody() {

        return "*" + subject + "*" + System.lineSeparator() + " - Occurred on: " + dateString() + System.lineSeparator() + " - Message: " + body;
    }

    private String dateString() {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        df.setTimeZone(tz);
        return df.format(date);
    }
}
