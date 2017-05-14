package com.edusalguero.rexoubador.domain.event.handler;

import com.edusalguero.rexoubador.domain.event.ServerObservableWasInactive;
import com.edusalguero.rexoubador.domain.model.event.Event;
import com.edusalguero.rexoubador.domain.model.event.EventId;
import com.edusalguero.rexoubador.domain.model.event.EventRepository;
import com.edusalguero.rexoubador.domain.model.server.Server;
import com.edusalguero.rexoubador.domain.model.server.observer.ServerObserver;
import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserRepository;
import com.edusalguero.rexoubador.domain.service.NotificationSenderService;
import com.edusalguero.rexoubador.domain.shared.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ServerObservableWasInactiveHandler implements EventHandler<ServerObservableWasInactive> {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final NotificationSenderService notificationsSender;

    @Autowired
    public ServerObservableWasInactiveHandler(UserRepository userRepository, EventRepository eventRepository, NotificationSenderService notificationsSender) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.notificationsSender = notificationsSender;
    }

    @Override
    public void handle(ServerObservableWasInactive event) {
        User user = userRepository.ofId(event.getUserId());
        Server server = user.server(event.getServerId());
        EventId eventId = eventRepository.nextIdentity();
        ServerObserver serverObserver = server.observer(event.getServerObserverId());
        Event serverEvent = server.recordEvent(eventId, user.contacts(), "Observer [" + serverObserver.observer().label() + "] is inactive in server [" + server.label() + "/ " + server.ip() + "]");
        notificationsSender.send(serverEvent);
    }
}
