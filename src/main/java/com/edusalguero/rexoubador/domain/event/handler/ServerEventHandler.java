package com.edusalguero.rexoubador.domain.event.handler;


import com.edusalguero.rexoubador.domain.model.contact.Contact;
import com.edusalguero.rexoubador.domain.model.event.EventId;
import com.edusalguero.rexoubador.domain.model.event.EventRepository;
import com.edusalguero.rexoubador.domain.model.server.Server;
import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserRepository;
import com.edusalguero.rexoubador.domain.service.notification.EmailService;
import com.edusalguero.rexoubador.domain.service.notification.NotificationMessage;
import com.edusalguero.rexoubador.domain.service.notification.SlackService;

import java.util.List;

abstract class ServerEventHandler {
    private final EmailService emailService;
    private final SlackService slackService;
    protected final UserRepository userRepository;
    private final EventRepository eventRepository;

    public ServerEventHandler(UserRepository userRepository, EventRepository eventRepository, EmailService emailService, SlackService slackService) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.emailService = emailService;
        this.slackService = slackService;
    }

    void createAndNotifyEvent(User user, Server server, NotificationMessage message) {
        EventId eventId = eventRepository.nextIdentity();
        List<Contact> contacts = user.contacts();
        server.recordEvent(eventId, user.contacts(), message.getBody());
        for (Contact contact : contacts) {
            if (contact.hasEmail()) {
                emailService.send(contact.email(), message);
            }
            if (contact.hasSlack()) {
                slackService.postMessage(contact.slackWebhookUrl(),contact.slackChannelOrUsername(), message);
            }
        }
    }
}
