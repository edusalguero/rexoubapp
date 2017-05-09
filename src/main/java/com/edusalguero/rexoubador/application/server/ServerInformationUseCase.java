package com.edusalguero.rexoubador.application.server;

import com.edusalguero.rexoubador.domain.model.server.Server;
import com.edusalguero.rexoubador.domain.model.server.ServerId;
import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserId;
import com.edusalguero.rexoubador.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerInformationUseCase {

    @Autowired
    private UserRepository userRepository;

    public ServerResponse execute(ServerId serverId, UserId userId) {
        User user = userRepository.ofId(userId);
        Server server = user.server(serverId);
        return new ServerResponse(server);
    }
}
