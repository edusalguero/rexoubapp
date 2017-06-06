package com.edusalguero.rexoubapp.application.user;

import com.edusalguero.rexoubapp.domain.model.user.User;
import com.edusalguero.rexoubapp.domain.model.user.UserId;
import com.edusalguero.rexoubapp.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDeleteUseCase {
    private final UserRepository userRepository;

    @Autowired
    public UserDeleteUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(UserId userId) {
        User user = userRepository.ofId(userId);
        user.delete();
        userRepository.update(user);
    }
}
