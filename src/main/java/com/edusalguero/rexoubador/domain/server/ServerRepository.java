package com.edusalguero.rexoubador.domain.server;

import com.edusalguero.rexoubador.domain.user.User;

import java.util.Collection;


public interface ServerRepository {

    Server ofId(ServerId serverId);

    Collection<Server> ofUser(User user);

    ServerId nextIdentity();

    void update(Server server);

}
