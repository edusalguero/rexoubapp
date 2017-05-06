package com.edusalguero.rexoubador.application.observer;

import com.edusalguero.rexoubador.domain.observer.ObserverId;
import com.edusalguero.rexoubador.domain.user.User;
import com.edusalguero.rexoubador.domain.user.UserId;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObserverDeleteUseCase {

    @Autowired
    private UserRepositoryJPA userRepository;

    public void execute(ObserverId observerId, UserId userId) {
        User user = userRepository.ofId(userId);
        user.deleteObserver(observerId);
        userRepository.update(user);
    }
}
