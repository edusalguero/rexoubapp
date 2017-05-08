package com.edusalguero.rexoubador.domain.model.server;

import com.edusalguero.rexoubador.domain.shared.UniqueId;
import com.edusalguero.rexoubador.domain.shared.ValueObject;


public class ServerId extends UniqueId implements ValueObject<ServerId> {
    protected ServerId() {
    }

    public ServerId(String string) {
        super(string);
    }

    @Override
    public boolean sameValueAs(ServerId other) {
        return other != null && this.id.equals(other.id);
    }
}
