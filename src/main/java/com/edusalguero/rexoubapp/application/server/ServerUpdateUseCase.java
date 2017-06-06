package com.edusalguero.rexoubapp.application.server;

import com.edusalguero.rexoubapp.domain.model.server.Server;
import com.edusalguero.rexoubapp.domain.model.user.User;
import com.edusalguero.rexoubapp.domain.model.user.UserRepository;
import com.edusalguero.rexoubapp.domain.shared.Status;
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
        if (! serverUpdateRequest.getIp().isEmpty())
        {
            server.updateIp(serverUpdateRequest.getIp());
        }

        server.updateLabel(serverUpdateRequest.getLabel());
        if(serverUpdateRequest.getStatus() == Status.DISABLED)
        {
            server.disable();
        }else {
            server.enable();
        }
        userRepository.update(user);
    }
}
