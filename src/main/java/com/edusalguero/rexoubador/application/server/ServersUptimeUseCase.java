package com.edusalguero.rexoubador.application.server;


import com.edusalguero.rexoubador.domain.model.server.Server;
import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserId;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServersUptimeUseCase {
    @Autowired
    private UserRepositoryJPA userRepository;

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
