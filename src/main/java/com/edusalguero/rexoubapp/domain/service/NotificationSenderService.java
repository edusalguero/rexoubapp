package com.edusalguero.rexoubapp.domain.service;

import com.edusalguero.rexoubapp.domain.model.event.Event;

public interface NotificationSenderService {
    void send(Event event);
}
