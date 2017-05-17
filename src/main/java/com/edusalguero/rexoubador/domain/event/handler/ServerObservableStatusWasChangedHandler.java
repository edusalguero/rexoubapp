package com.edusalguero.rexoubador.domain.event.handler;

import com.edusalguero.rexoubador.domain.event.ServerObservableStatusWasChanged;
import com.edusalguero.rexoubador.domain.model.event.EventRepository;
import com.edusalguero.rexoubador.domain.model.server.Server;
import com.edusalguero.rexoubador.domain.model.server.observer.ServerObserver;
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
public class ServerObservableStatusWasChangedHandler extends ServerEventHandler implements EventHandler<ServerObservableStatusWasChanged> {


    @Autowired
    public ServerObservableStatusWasChangedHandler(UserRepository userRepository, EventRepository eventRepository, EmailService emailService, SlackService slackService) {
        super(userRepository, eventRepository, emailService, slackService);
    }

    @Override
    public void handle(ServerObservableStatusWasChanged event) {
        User user = userRepository.ofId(event.getUserId());
        Server server = user.server(event.getServerId());
        ServerObserver serverObserver = server.observer(event.getServerObserverId());
        String body = "Observer [" + serverObserver.observer().label() + "] status was changed to [" + event.getCheckStatus() + "] in server [" + server.label() + " / " + server.ip() + "]";
        NotificationMessage notificationMessage = new EventMessage("Observer status was changed",body, event.occurredOn() );
        createAndNotifyEvent(user, server,notificationMessage, serverObserver.notifyStatusChanges());
    }
}
