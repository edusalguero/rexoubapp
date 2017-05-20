package com.edusalguero.rexoubador.domain.model.contact;

import com.edusalguero.rexoubador.domain.shared.NotFoundException;

public class ContactNotFoundException extends NotFoundException {
    public ContactNotFoundException() {
        super("00001","Contact not found!");
    }
}
