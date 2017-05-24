package com.edusalguero.rexoubador.application.user;

import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserId;
import com.edusalguero.rexoubador.domain.model.user.UserRepository;
import com.edusalguero.rexoubador.domain.shared.ValidationException;
import com.edusalguero.rexoubador.domain.shared.validator.UsernameUniqueValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class UserRegistrationUseCase {

    private final UserRepository userRepository;
    private final UsernameUniqueValidator usernameUniqueValidator;

    @Autowired
    public UserRegistrationUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.usernameUniqueValidator = new UsernameUniqueValidator(this.userRepository);
    }

    public UserId execute(String username, String password, String firstName, String lastName) {
        UserId uid = userRepository.nextIdentity();

        if(!usernameUniqueValidator.validate(username))
        {
            throw new ValidationException("Invalid username! Select a different one");
        }
        User u = new User(uid, username, password, firstName, lastName);
        userRepository.register(u);
        return uid;
    }

}
