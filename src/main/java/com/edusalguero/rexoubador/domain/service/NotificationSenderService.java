package com.edusalguero.rexoubador.domain.service;

import com.edusalguero.rexoubador.domain.model.event.Event;

public interface NotificationSenderService {
    void send(Event event);
}
