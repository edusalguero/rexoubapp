package com.edusalguero.rexoubador.application.monitor.observer;

import com.edusalguero.rexoubador.domain.model.monitor.observer.Observer;
import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObserverUpdateUseCase {
    private final UserRepository userRepository;

    @Autowired
    public ObserverUpdateUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(ObserverUpdateRequest observerUpdateRequest) {
        User user = userRepository.ofId(observerUpdateRequest.getUserId());
        Observer observer = user.observer(observerUpdateRequest.getObserverId());
        observer.notifyStatusChanges(observerUpdateRequest.getNotifyStatusChanges());
        observer.notifyInactivity(observerUpdateRequest.getNotifyInactivity());
        observer.label(observerUpdateRequest.getLabel());
        userRepository.update(user);
    }
}
