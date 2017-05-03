package com.edusalguero.rexoubador.domain.event;

import com.edusalguero.rexoubador.domain.shared.UniqueId;
import com.edusalguero.rexoubador.domain.shared.ValueObject;

public class EventId extends UniqueId implements ValueObject<EventId> {
    protected EventId() {
    }

    public EventId(String string) {
        super(string);
    }

    @Override
    public boolean sameValueAs(EventId other) {
        return other != null && this.id.equals(other.id);
    }
}
