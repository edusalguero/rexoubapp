package com.edusalguero.rexoubador.domain.monitor.observer;

import com.edusalguero.rexoubador.domain.shared.UniqueId;
import com.edusalguero.rexoubador.domain.shared.ValueObject;

public class ObserverId extends UniqueId implements ValueObject<ObserverId> {
    protected ObserverId() {
    }

    public ObserverId(String string) {
        super(string);
    }

    @Override
    public boolean sameValueAs(ObserverId other) {
        return other != null && this.id.equals(other.id);
    }
}
