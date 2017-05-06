package com.edusalguero.rexoubador.application.monitor.observer;

import com.edusalguero.rexoubador.domain.monitor.observer.Observer;
import com.edusalguero.rexoubador.domain.user.User;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObserverUpdateUseCase {
    @Autowired
    private UserRepositoryJPA userRepository;

    public void execute(ObserverUpdateRequest observerUpdateRequest) {
        User user = userRepository.ofId(observerUpdateRequest.getUserId());
        Observer observer = user.observer(observerUpdateRequest.getObserverId());
        observer.notifyStatusChanges(observerUpdateRequest.getNotifyStatusChanges());
        observer.notifyInactivity(observerUpdateRequest.getNotifyInactivity());
        observer.label(observerUpdateRequest.getLabel());
        userRepository.update(user);
    }
}
