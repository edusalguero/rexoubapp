package com.edusalguero.rexoubapp.domain.event.handler;


import com.edusalguero.rexoubapp.domain.model.contact.Contact;
import com.edusalguero.rexoubapp.domain.model.event.EventId;
import com.edusalguero.rexoubapp.domain.model.event.EventRepository;
import com.edusalguero.rexoubapp.domain.model.server.Server;
import com.edusalguero.rexoubapp.domain.model.user.User;
import com.edusalguero.rexoubapp.domain.model.user.UserRepository;
import com.edusalguero.rexoubapp.domain.service.notification.EmailService;
import com.edusalguero.rexoubapp.domain.service.notification.NotificationMessage;
import com.edusalguero.rexoubapp.domain.service.notification.SlackService;

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
        createAndNotifyEvent(user, server, message, true);
    }

    void createAndNotifyEvent(User user, Server server, NotificationMessage message, Boolean notifyToContacts) {
        EventId eventId = eventRepository.nextIdentity();
        List<Contact> contacts = user.contacts();
        server.recordEvent(eventId, user.contacts(), message.getBody());
        if (notifyToContacts) {
            for (Contact contact : contacts) {
                if (contact.hasEmail()) {
                    emailService.send(contact.email(), message);
                }
                if (contact.hasSlack()) {
                    slackService.postMessage(contact.slackWebhookUrl(), contact.slackChannelOrUsername(), message);
                }
            }
        }
    }
}
