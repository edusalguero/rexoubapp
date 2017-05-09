package com.edusalguero.rexoubador.application.monitor.observer;


import com.edusalguero.rexoubador.domain.model.monitor.observer.ObserverId;
import com.edusalguero.rexoubador.domain.model.monitor.observer.ObserverRepository;
import com.edusalguero.rexoubador.domain.model.monitor.observer.ObserverType;
import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserId;
import com.edusalguero.rexoubador.domain.model.user.UserRepository;
import com.edusalguero.rexoubador.domain.shared.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObserverCreateUseCase {

    @Autowired
    private ObserverRepository checkRepository;
    @Autowired
    private UserRepository userRepository;

    public ObserverId execute(UserId userId, ObserverType type, String name, String label, Boolean notifyStatusChanges, Boolean notifyInactivity, Status status) {
        ObserverId observerId = checkRepository.nextIdentity();
        User user = userRepository.ofId(userId);
        user.addObserver(observerId, type, name, label, notifyStatusChanges, notifyInactivity, status);
        userRepository.update(user);
        return observerId;
    }
}
