package com.edusalguero.rexoubador.application.user;

import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserRepository;
import com.edusalguero.rexoubador.domain.model.user.service.HashingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUpdateUseCase {
    private final UserRepository userRepository;
    private HashingService hashingService;

    @Autowired
    public UserUpdateUseCase(UserRepository userRepository, HashingService hashingService) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
    }

    public void execute(UserUpdateRequest userUpdateRequest) {
        User user = userRepository.ofId(userUpdateRequest.getUserId());
        user.setHashingService(hashingService);
        if (userUpdateRequest.passwordHasChanged()) {
            user.changePassword(userUpdateRequest.getPassword());
        }

        if (userUpdateRequest.getFirstName()!=null) {
            user.firstName(userUpdateRequest.getFirstName());
        }
        if (userUpdateRequest.getLastName()!=null) {
            user.lastName(userUpdateRequest.getLastName());
        }
        userRepository.update(user);
    }
}
