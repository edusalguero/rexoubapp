package com.edusalguero.rexoubador.domain.model.server;

import com.edusalguero.rexoubador.domain.model.user.User;

import java.util.Collection;


public interface ServerRepository {

    Server ofId(ServerId serverId);

    Collection<Server> ofUser(User user);

    ServerId nextIdentity();

    Collection<Server> toBeCollected();

    void update(Server server);

}
