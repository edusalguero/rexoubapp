package com.edusalguero.rexoubapp.domain.event.handler;

import com.edusalguero.rexoubapp.domain.event.ServerObservableWasInactive;
import com.edusalguero.rexoubapp.domain.model.event.EventRepository;
import com.edusalguero.rexoubapp.domain.model.server.Server;
import com.edusalguero.rexoubapp.domain.model.server.observer.ServerObserver;
import com.edusalguero.rexoubapp.domain.model.user.User;
import com.edusalguero.rexoubapp.domain.model.user.UserRepository;
import com.edusalguero.rexoubapp.domain.service.notification.EmailService;
import com.edusalguero.rexoubapp.domain.service.notification.EventMessage;
import com.edusalguero.rexoubapp.domain.service.notification.NotificationMessage;
import com.edusalguero.rexoubapp.domain.service.notification.SlackService;
import com.edusalguero.rexoubapp.domain.shared.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ServerObservableWasInactiveHandler extends ServerEventHandler implements EventHandler<ServerObservableWasInactive> {

    @Autowired
    public ServerObservableWasInactiveHandler(UserRepository userRepository, EventRepository eventRepository, EmailService emailService, SlackService slackService) {
        super(userRepository, eventRepository, emailService, slackService);
    }

    @Override
    public void handle(ServerObservableWasInactive event) {
        User user = userRepository.ofId(event.getUserId());
        Server server = user.server(event.getServerId());
        ServerObserver serverObserver = server.observer(event.getServerObserverId());
        String body = "Observer [" + serverObserver.observer().label() + "] is inactive in server [" + server.label() + " / " + server.ip() + "]";
        NotificationMessage notificationMessage = new EventMessage("Observer is inactive", body, event.occurredOn());
        createAndNotifyEvent(user, server, notificationMessage, serverObserver.notifyInactivity());
    }
}
