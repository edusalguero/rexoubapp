package com.edusalguero.rexoubador.domain.contact;

public class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException() {
        super("Contact not found!");
    }
}
