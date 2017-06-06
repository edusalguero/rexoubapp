package com.edusalguero.rexoubapp.domain.model.user;

import com.edusalguero.rexoubapp.domain.shared.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super("00010","User not found!");
    }
}
