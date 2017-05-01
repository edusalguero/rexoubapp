package com.edusalguero.rexoubador.application.server;

import com.edusalguero.rexoubador.domain.server.ServerId;
import com.edusalguero.rexoubador.domain.user.User;
import com.edusalguero.rexoubador.domain.user.UserId;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerDeleteUseCase {

    @Autowired
    private UserRepositoryJPA userRepository;

    public void execute(ServerId serverId, UserId userId)
    {
        User user = userRepository.ofId(userId);
        user.deleteServer(serverId);
        userRepository.update(user);
    }
}
