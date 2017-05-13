package com.edusalguero.rexoubador.application.server;

import com.edusalguero.rexoubador.domain.model.server.Server;
import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserId;
import com.edusalguero.rexoubador.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServersUseCase {

    private final UserRepository userRepository;

    @Autowired
    public UserServersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ArrayList<ServerResponse> execute(UserId userId) {
        ArrayList<ServerResponse> userServers = new ArrayList<>();
        User user = userRepository.ofId(userId);
        List<Server> servers = user.servers();
        for (Server server : servers) {
            userServers.add(new ServerResponse(server));
        }
        return userServers;
    }
}
