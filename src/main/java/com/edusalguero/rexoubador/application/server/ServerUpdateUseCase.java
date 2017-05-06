package com.edusalguero.rexoubador.application.server;

import com.edusalguero.rexoubador.domain.server.Server;
import com.edusalguero.rexoubador.domain.user.User;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerUpdateUseCase {

    @Autowired
    private UserRepositoryJPA userRepository;

    public void execute(ServerUpdateRequest serverUpdateRequest) {
        User user = userRepository.ofId(serverUpdateRequest.getUserId());
        Server server = user.server(serverUpdateRequest.getServerId());
        server.updateIp(serverUpdateRequest.getIp());
        server.updateLabel(serverUpdateRequest.getLabel());
        server.updateStatus(serverUpdateRequest.getStatus());
        userRepository.update(user);
    }
}
