package com.edusalguero.rexoubapp.application.monitor.observer;


import com.edusalguero.rexoubapp.domain.model.monitor.observer.ObserverId;
import com.edusalguero.rexoubapp.domain.model.monitor.observer.ObserverRepository;
import com.edusalguero.rexoubapp.domain.model.monitor.observer.ObserverType;
import com.edusalguero.rexoubapp.domain.model.user.User;
import com.edusalguero.rexoubapp.domain.model.user.UserId;
import com.edusalguero.rexoubapp.domain.model.user.UserRepository;
import com.edusalguero.rexoubapp.domain.shared.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObserverCreateUseCase {

    private final ObserverRepository checkRepository;
    private final UserRepository userRepository;

    @Autowired
    public ObserverCreateUseCase(ObserverRepository checkRepository, UserRepository userRepository) {
        this.checkRepository = checkRepository;
        this.userRepository = userRepository;
    }

    public ObserverId execute(UserId userId, ObserverType type, String name, String label, Boolean notifyStatusChanges, Boolean notifyInactivity, Status status) {
        ObserverId observerId = checkRepository.nextIdentity();
        User user = userRepository.ofId(userId);
        user.addObserver(observerId, type, name, label, notifyStatusChanges, notifyInactivity, status);
        userRepository.update(user);
        return observerId;
    }
}
