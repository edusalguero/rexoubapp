package com.edusalguero.rexoubador.application.server;

import com.edusalguero.rexoubador.application.contact.ContactResponse;
import com.edusalguero.rexoubador.domain.server.Server;
import com.edusalguero.rexoubador.domain.user.User;
import com.edusalguero.rexoubador.domain.user.UserId;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServersUseCase {

    @Autowired
    private UserRepositoryJPA userRepository;

    public ArrayList<ServerResponse> execute(UserId userId) {
        ArrayList<ServerResponse> userServers = new ArrayList<>();
        User user = userRepository.ofId(userId);
        List<Server> contacts = user.servers();
        for (Server server : contacts) {
            userServers.add(new ServerResponse(server));
        }
        return userServers;
    }
}
