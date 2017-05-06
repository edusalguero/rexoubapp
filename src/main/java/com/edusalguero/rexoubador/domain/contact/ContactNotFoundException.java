package com.edusalguero.rexoubador.domain.contact;

import com.edusalguero.rexoubador.domain.shared.NotFoundException;

public class ContactNotFoundException extends NotFoundException {
    public ContactNotFoundException() {
        super("Contact not found!");
    }
}
