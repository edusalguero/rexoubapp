package com.edusalguero.rexoubapp.domain.shared.validator;


import com.edusalguero.rexoubapp.domain.model.user.UserRepository;

import java.math.BigInteger;

public class UsernameUniqueValidator implements ValidatorInterface<String> {

    private UserRepository userRepository;

    public UsernameUniqueValidator(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public boolean validate(String username) {
        BigInteger users = userRepository.countOfUsername(username);
        if (users.intValue() == 0) {
            return true;
        }
        return false;
    }
}
