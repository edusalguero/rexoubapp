package com.edusalguero.rexoubapp.application.user;

import com.edusalguero.rexoubapp.domain.model.user.User;
import com.edusalguero.rexoubapp.domain.model.user.UserId;
import com.edusalguero.rexoubapp.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInformationUseCase {

    private final UserRepository userRepository;

    @Autowired
    public UserInformationUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserInformationResponse execute(UserId userId) {
        User user = userRepository.ofId(userId);
        return new UserInformationResponse(user);
    }
}
