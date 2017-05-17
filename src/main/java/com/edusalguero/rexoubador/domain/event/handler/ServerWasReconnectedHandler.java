package com.edusalguero.rexoubador.domain.event.handler;

import com.edusalguero.rexoubador.domain.event.ServerWasReconnected;
import com.edusalguero.rexoubador.domain.model.event.EventRepository;
import com.edusalguero.rexoubador.domain.model.server.Server;
import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserRepository;
import com.edusalguero.rexoubador.domain.service.notification.EmailService;
import com.edusalguero.rexoubador.domain.service.notification.EventMessage;
import com.edusalguero.rexoubador.domain.service.notification.NotificationMessage;
import com.edusalguero.rexoubador.domain.service.notification.SlackService;
import com.edusalguero.rexoubador.domain.shared.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ServerWasReconnectedHandler extends ServerEventHandler implements EventHandler<ServerWasReconnected> {

    @Autowired
    public ServerWasReconnectedHandler(UserRepository userRepository, EventRepository eventRepository, EmailService emailService, SlackService slackService) {
        super(userRepository, eventRepository, emailService, slackService);
    }

    @Override
    public void handle(ServerWasReconnected event) {
        User user = userRepository.ofId(event.getUserId());
        Server server = user.server(event.getServerId());
        String body = "Server [" + server.label() + " / " + server.ip() + "] was reconnected";
        NotificationMessage notificationMessage = new EventMessage("Server was reconnected", body, event.occurredOn());
        createAndNotifyEvent(user, server, notificationMessage);
    }


}
