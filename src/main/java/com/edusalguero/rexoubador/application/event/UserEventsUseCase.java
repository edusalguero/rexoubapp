package com.edusalguero.rexoubador.application.event;

import com.edusalguero.rexoubador.domain.event.Event;
import com.edusalguero.rexoubador.domain.user.User;
import com.edusalguero.rexoubador.domain.user.UserId;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.EventRepositoryJPA;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserEventsUseCase {

    @Autowired
    private UserRepositoryJPA userRepository;
    @Autowired
    private EventRepositoryJPA eventRepository;

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
