package com.edusalguero.rexoubador.domain.user;

import com.edusalguero.rexoubador.domain.shared.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super("User not found!");
    }
}
