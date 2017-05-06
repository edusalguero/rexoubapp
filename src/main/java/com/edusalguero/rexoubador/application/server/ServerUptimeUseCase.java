package com.edusalguero.rexoubador.application.server;

import com.edusalguero.rexoubador.domain.server.Server;
import com.edusalguero.rexoubador.domain.server.ServerId;
import com.edusalguero.rexoubador.domain.user.User;
import com.edusalguero.rexoubador.domain.user.UserId;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerUptimeUseCase {

    @Autowired
    private UserRepositoryJPA userRepository;

    public ServerUptimeResponse execute(ServerId serverId, UserId userId) {
        User user = userRepository.ofId(userId);
        Server server = user.server(serverId);
        return new ServerUptimeResponse(server);
    }
}
