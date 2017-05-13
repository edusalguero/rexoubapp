package com.edusalguero.rexoubador.application.server;

import com.edusalguero.rexoubador.domain.model.server.ServerId;
import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserId;
import com.edusalguero.rexoubador.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerDeleteUseCase {

    private final UserRepository userRepository;

    @Autowired
    public ServerDeleteUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(ServerId serverId, UserId userId) {
        User user = userRepository.ofId(userId);
        user.deleteServer(serverId);
        userRepository.update(user);
    }
}
