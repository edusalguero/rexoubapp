package com.edusalguero.rexoubapp.application.server;


import com.edusalguero.rexoubapp.domain.model.server.Server;
import com.edusalguero.rexoubapp.domain.model.user.User;
import com.edusalguero.rexoubapp.domain.model.user.UserId;
import com.edusalguero.rexoubapp.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServersUptimeUseCase {
    private final UserRepository userRepository;

    @Autowired
    public ServersUptimeUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ArrayList<ServerUptimeResponse> execute(UserId userId) {
        ArrayList<ServerUptimeResponse> serversUptime = new ArrayList<>();
        User user = userRepository.ofId(userId);
        List<Server> servers = user.servers();
        for (Server server : servers) {
            if (!server.isEnabled()) {
                continue;
            }
            serversUptime.add(new ServerUptimeResponse(server));
        }
        return serversUptime;
    }
}
