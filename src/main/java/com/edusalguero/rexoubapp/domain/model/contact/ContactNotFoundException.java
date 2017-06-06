package com.edusalguero.rexoubapp.domain.model.contact;

import com.edusalguero.rexoubapp.domain.shared.NotFoundException;

public class ContactNotFoundException extends NotFoundException {
    public ContactNotFoundException() {
        super("00001","Contact not found!");
    }
}
