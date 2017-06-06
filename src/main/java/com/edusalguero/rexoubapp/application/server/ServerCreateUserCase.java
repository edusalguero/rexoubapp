package com.edusalguero.rexoubapp.application.server;

import com.edusalguero.rexoubapp.domain.model.server.ServerId;
import com.edusalguero.rexoubapp.domain.model.server.ServerRepository;
import com.edusalguero.rexoubapp.domain.model.user.User;
import com.edusalguero.rexoubapp.domain.model.user.UserId;
import com.edusalguero.rexoubapp.domain.model.user.UserRepository;
import com.edusalguero.rexoubapp.domain.shared.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ServerCreateUserCase {

    private final String publicSSHKey;
    private final ServerRepository serverRepository;
    private final UserRepository userRepository;

    @Autowired
    public ServerCreateUserCase(@Value("${rexoubapp.publicSSHKey}") String publicSSHKey, ServerRepository serverRepository, UserRepository userRepository) {
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
