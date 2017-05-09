package com.edusalguero.rexoubador.application.user;

import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserId;
import com.edusalguero.rexoubador.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationUseCase {

    @Autowired
    private UserRepository userRepository;

    public UserId execute(String username, String password, String firstName, String lastName) {
        UserId uid = userRepository.nextIdentity();
        User u = new User(uid, username, password, firstName, lastName);
        userRepository.register(u);
        return uid;
    }

}
