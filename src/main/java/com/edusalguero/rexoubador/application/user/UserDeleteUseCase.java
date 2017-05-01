package com.edusalguero.rexoubador.application.user;

import com.edusalguero.rexoubador.domain.Status;
import com.edusalguero.rexoubador.domain.user.User;
import com.edusalguero.rexoubador.domain.user.UserId;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDeleteUseCase {
    @Autowired
    private UserRepositoryJPA userRepository;

    public void execute(UserId userId) {
        User user = userRepository.ofId(userId);
        user.updateStatus(Status.DISABLED);
        userRepository.update(user);
    }
}
