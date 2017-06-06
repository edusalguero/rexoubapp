package com.edusalguero.rexoubapp.domain.model.user;


import com.edusalguero.rexoubapp.domain.shared.UniqueId;
import com.edusalguero.rexoubapp.domain.shared.ValueObject;


public final class UserId extends UniqueId implements ValueObject<UserId> {

    protected UserId() {
    }

    public UserId(String string) {
        super(string);
    }

    @Override
    public boolean sameValueAs(UserId other) {
        return other != null && this.id.equals(other.id);
    }
}
