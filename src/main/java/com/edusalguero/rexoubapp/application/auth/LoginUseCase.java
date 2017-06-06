package com.edusalguero.rexoubapp.application.auth;

import com.edusalguero.rexoubapp.domain.model.user.User;
import com.edusalguero.rexoubapp.domain.model.user.UserNotFoundException;
import com.edusalguero.rexoubapp.domain.model.user.UserRepository;
import com.edusalguero.rexoubapp.domain.model.user.service.HashingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class LoginUseCase {

    private String secret;

    private final UserRepository userRepository;

    private final TokenGenerator tokenGenerator;

    private HashingService hashingService;

    @Autowired
    public LoginUseCase(@Value("${jwt.secret}") String secret, UserRepository userRepository, TokenGenerator tokenGenerator, HashingService hashingService) {
        this.secret = secret;
        this.userRepository = userRepository;
        this.tokenGenerator = tokenGenerator;
        this.hashingService = hashingService;
    }

    public LoginResponse execute(String username, String password) {
        User u;
        try {
            u = userRepository.ofUsername(username);
            u.setHashingService(hashingService);
            if (!u.matchPassword(password) || !u.isEnabled()) {
                throw new InvalidUsernameOrPassword();
            }

        } catch (UserNotFoundException e) {
            throw new InvalidUsernameOrPassword();
        }

        return new LoginResponse(tokenGenerator.generateToken(u, secret));
    }

}
