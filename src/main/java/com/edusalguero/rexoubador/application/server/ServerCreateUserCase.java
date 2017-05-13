package com.edusalguero.rexoubador.application.server;

import com.edusalguero.rexoubador.domain.model.server.ServerId;
import com.edusalguero.rexoubador.domain.model.server.ServerRepository;
import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserId;
import com.edusalguero.rexoubador.domain.model.user.UserRepository;
import com.edusalguero.rexoubador.domain.shared.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ServerCreateUserCase {

    private final String publicSSHKey;
    private final ServerRepository serverRepository;
    private final UserRepository userRepository;

    @Autowired
    public ServerCreateUserCase(@Value("${rexoubador.publicSSHKey}") String publicSSHKey, ServerRepository serverRepository, UserRepository userRepository) {
        this.publicSSHKey = publicSSHKey;
        this.serverRepository = serverRepository;
        this.userRepository = userRepository;
    }

    public ServerCreateResponse execute(UserId userId, String label, String ip, Status status) {
        ServerId serverId = serverRepository.nextIdentity();
        User user = userRepository.ofId(userId);
        user.addServer(serverId, label, ip, status);
        userRepository.update(user);
        return new ServerCreateResponse(serverId, publicSSHKey);
    }
}
