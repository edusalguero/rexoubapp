package com.edusalguero.rexoubador.application.auth;

import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserNotFoundException;
import com.edusalguero.rexoubador.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class LoginUseCase {

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenGenerator tokenGenerator;

    public LoginResponse execute(String username, String password) {
        User u;
        try {
            u = userRepository.ofUsername(username);

            if (!u.matchPassword(password) || !u.isEnabled()) {
                throw new InvalidUsernameOrPassword();
            }

        } catch (UserNotFoundException e) {
            throw new InvalidUsernameOrPassword();
        }

        return new LoginResponse(tokenGenerator.generateToken(u, secret));
    }

}
