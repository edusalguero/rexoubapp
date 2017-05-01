package com.edusalguero.rexoubador.application.user;

import com.edusalguero.rexoubador.domain.user.User;
import com.edusalguero.rexoubador.domain.user.UserId;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserRegistrationUseCase {

    @Autowired
    private UserRepositoryJPA userRepository;

    public UserId execute(String username, String password, String firstName, String lastName) {
        UserId uid = userRepository.nextIdentity();
        User u = new User(uid, username, password, firstName, lastName);
        userRepository.register(u);
        return uid;
    }

}
