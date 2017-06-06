package com.edusalguero.rexoubapp.domain.model.server;

import com.edusalguero.rexoubapp.domain.model.user.User;

import java.util.Collection;


public interface ServerRepository {

    Server ofId(ServerId serverId);

    Collection<Server> ofUser(User user);

    ServerId nextIdentity();

    Collection<Server> toBeCollected();

    void update(Server server);

}
