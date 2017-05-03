package com.edusalguero.rexoubador.application.user;

import com.edusalguero.rexoubador.domain.user.User;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUpdateUseCase {
    @Autowired
    private UserRepositoryJPA userRepository;

    public void execute(UserUpdateRequest userUpdateRequest) {
        User user = userRepository.ofId(userUpdateRequest.getUserId());
        if (userUpdateRequest.passwordHasChanged()) {
            user.changePassword(userUpdateRequest.getPassword());
        }

        if (userUpdateRequest.nameHasChanged()) {
            user.lastName(userUpdateRequest.getLastName());
            user.firstName(userUpdateRequest.getFirstName());
        }
        userRepository.update(user);
    }
}
