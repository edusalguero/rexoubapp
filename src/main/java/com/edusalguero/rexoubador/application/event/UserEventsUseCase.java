package com.edusalguero.rexoubador.application.event;

import com.edusalguero.rexoubador.domain.model.event.Event;
import com.edusalguero.rexoubador.domain.model.event.EventRepository;
import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserId;
import com.edusalguero.rexoubador.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserEventsUseCase {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Autowired
    public UserEventsUseCase(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public ArrayList<EventResponse> execute(UserId userId) {
        User user = userRepository.ofId(userId);
        ArrayList<EventResponse> eventResponses = new ArrayList<>();
        Collection<Event> events = eventRepository.ofUser(user);
        for (Event event : events) {
            eventResponses.add(new EventResponse(event));
        }
        return eventResponses;
    }
}
