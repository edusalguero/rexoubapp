package com.edusalguero.rexoubapp.domain.model.user;


import java.math.BigInteger;

public interface UserRepository {
    User ofId(UserId userId);

    BigInteger countOfUsername(String username);

    User ofUsername(String username);

    void register(User user);

    void update(User user);

    UserId nextIdentity();

}
