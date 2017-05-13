package com.edusalguero.rexoubador.application.server;

import com.edusalguero.rexoubador.domain.model.server.Server;
import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerUpdateUseCase {

    private final UserRepository userRepository;

    @Autowired
    public ServerUpdateUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(ServerUpdateRequest serverUpdateRequest) {
        User user = userRepository.ofId(serverUpdateRequest.getUserId());
        Server server = user.server(serverUpdateRequest.getServerId());
        server.updateIp(serverUpdateRequest.getIp());
        server.updateLabel(serverUpdateRequest.getLabel());
        server.updateStatus(serverUpdateRequest.getStatus());
        userRepository.update(user);
    }
}
