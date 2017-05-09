package com.edusalguero.rexoubador.application.user;

import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserId;
import com.edusalguero.rexoubador.domain.model.user.UserRepository;
import com.edusalguero.rexoubador.domain.shared.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDeleteUseCase {
    @Autowired
    private UserRepository userRepository;

    public void execute(UserId userId) {
        User user = userRepository.ofId(userId);
        user.updateStatus(Status.DISABLED);
        userRepository.update(user);
    }
}
