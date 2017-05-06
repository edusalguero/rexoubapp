package com.edusalguero.rexoubador.application.monitor.observer;


import com.edusalguero.rexoubador.domain.Status;
import com.edusalguero.rexoubador.domain.monitor.observer.ObserverId;
import com.edusalguero.rexoubador.domain.monitor.observer.ObserverType;
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

    public ObserverId execute(UserId userId, ObserverType type, String name, String label, Boolean notifyStatusChanges, Boolean notifyInactivity, Status status) {
        ObserverId observerId = checkRepository.nextIdentity();
        User user = userRepository.ofId(userId);
        user.addObserver(observerId, type, name, label, notifyStatusChanges, notifyInactivity, status);
        userRepository.update(user);
        return observerId;
    }
}
