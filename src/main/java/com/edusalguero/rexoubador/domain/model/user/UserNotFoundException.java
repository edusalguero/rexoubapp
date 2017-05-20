package com.edusalguero.rexoubador.domain.model.user;

import com.edusalguero.rexoubador.domain.shared.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super("00010","User not found!");
    }
}
