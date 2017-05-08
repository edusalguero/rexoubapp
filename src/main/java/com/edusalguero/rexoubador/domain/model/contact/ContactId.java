package com.edusalguero.rexoubador.domain.model.contact;

import com.edusalguero.rexoubador.domain.shared.UniqueId;
import com.edusalguero.rexoubador.domain.shared.ValueObject;

public class ContactId extends UniqueId implements ValueObject<ContactId> {
    protected ContactId() {
    }

    public ContactId(String string) {
        super(string);
    }

    @Override
    public boolean sameValueAs(ContactId other) {
        return other != null && this.id.equals(other.id);
    }
}
