package com.edusalguero.rexoubapp.application.server;

import com.edusalguero.rexoubapp.domain.model.server.Server;
import com.edusalguero.rexoubapp.domain.model.server.ServerId;
import com.edusalguero.rexoubapp.domain.model.user.User;
import com.edusalguero.rexoubapp.domain.model.user.UserId;
import com.edusalguero.rexoubapp.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerInformationUseCase {

    private final UserRepository userRepository;

    @Autowired
    public ServerInformationUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ServerFullResponse execute(ServerId serverId, UserId userId) {
        User user = userRepository.ofId(userId);
        Server server = user.server(serverId);
        return new ServerFullResponse(server);
    }
}
