package com.edusalguero.rexoubador.application.observer;


import com.edusalguero.rexoubador.domain.observer.ObserverId;
import com.edusalguero.rexoubador.domain.observer.ObserverType;
import com.edusalguero.rexoubador.domain.user.User;
import com.edusalguero.rexoubador.domain.user.UserId;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.ObserverRepositoryJPA;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObserverCreateUseCase {

    @Autowired
    private ObserverRepositoryJPA checkRepository;
    @Autowired
    private UserRepositoryJPA userRepository;

    public ObserverId execute(UserId userId, ObserverType type, String name, String label, Boolean notifyStatusChanges, Boolean notifyInactivity) {
        ObserverId observerId = checkRepository.nextIdentity();
        User user = userRepository.ofId(userId);
        user.addObserver(observerId, type, name, label, notifyStatusChanges, notifyInactivity);
        userRepository.update(user);
        return observerId;
    }
}
