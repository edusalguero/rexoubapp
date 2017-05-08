package com.edusalguero.rexoubador.domain.model.user;


public interface UserRepository {
    User ofId(UserId userId);

    User ofUsername(String username);

    void register(User user);

    void update(User user);

    UserId nextIdentity();

}
