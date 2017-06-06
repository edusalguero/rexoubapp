package com.edusalguero.rexoubapp.domain.model.contact;

import com.edusalguero.rexoubapp.domain.shared.UniqueId;
import com.edusalguero.rexoubapp.domain.shared.ValueObject;

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
