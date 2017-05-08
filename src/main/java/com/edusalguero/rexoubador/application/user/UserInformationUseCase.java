package com.edusalguero.rexoubador.application.user;

import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserId;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInformationUseCase {

    @Autowired
    private UserRepositoryJPA userRepository;

    public UserInformationResponse execute(UserId userId) {
        User user = userRepository.ofId(userId);
        return new UserInformationResponse(user,
                0);
    }
}
