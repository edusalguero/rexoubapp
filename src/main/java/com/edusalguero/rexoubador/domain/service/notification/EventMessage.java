package com.edusalguero.rexoubador.domain.service.notification;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class EventMessage implements NotificationMessage {
    private String subject;
    private String body;
    private Date date;
    private HashMap<String, String> extraData = new HashMap<String, String>();

    public EventMessage(String subject, String body, Date date) {
        this.subject = subject;
        this.body = body;
        this.date = date;
    }

    public void addExtraData(String property, String value) {
        extraData.put(property, value);
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public String getTextBody() {
        return subject + System.lineSeparator() + " - Occurred on: " + dateString() + System.lineSeparator()
                + " - Message: " + body + getExtraData();
    }

    public String getMarkdownBody() {
        return "*" + subject + "*" + System.lineSeparator() + " - Occurred on: " + dateString() + System.lineSeparator()
                + " - Message: " + body + getExtraData();
    }

    private String getExtraData() {
        final StringBuilder chars = new StringBuilder();
        extraData.forEach((k, v) -> {
            chars.append(System.lineSeparator()).append(" - ").append(k).append(": ").append(v);
        });
        return String.valueOf(chars);
    }

    private String dateString() {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        df.setTimeZone(tz);
        return df.format(date);
    }
}
