package com.edusalguero.rexoubapp.application.event;

import com.edusalguero.rexoubapp.domain.model.event.Event;
import com.edusalguero.rexoubapp.domain.model.event.EventRepository;
import com.edusalguero.rexoubapp.domain.model.user.User;
import com.edusalguero.rexoubapp.domain.model.user.UserId;
import com.edusalguero.rexoubapp.domain.model.user.UserRepository;
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

    public ArrayList<EventResponse> execute(UserId userId, int days) {
        User user = userRepository.ofId(userId);
        ArrayList<EventResponse> eventResponses = new ArrayList<>();
        Collection<Event> events = eventRepository.ofUserInDays(user, days);
        for (Event event : events) {
            eventResponses.add(new EventResponse(event));
        }
        return eventResponses;
    }
}
