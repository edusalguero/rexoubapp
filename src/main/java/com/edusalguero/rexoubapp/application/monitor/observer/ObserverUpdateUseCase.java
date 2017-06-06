package com.edusalguero.rexoubapp.application.monitor.observer;

import com.edusalguero.rexoubapp.domain.model.monitor.observer.Observer;
import com.edusalguero.rexoubapp.domain.model.user.User;
import com.edusalguero.rexoubapp.domain.model.user.UserRepository;
import com.edusalguero.rexoubapp.domain.shared.Status;
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
        if (observerUpdateRequest.getStatus() == Status.DISABLED) {
            observer.disable();
        } else {
            observer.enable();
        }
        if (observerUpdateRequest.getNotifyStatusChanges() != null) {
            observer.notifyStatusChanges(observerUpdateRequest.getNotifyStatusChanges());
        }
        if (observerUpdateRequest.getNotifyInactivity() != null) {
            observer.notifyInactivity(observerUpdateRequest.getNotifyInactivity());
        }
        if (observerUpdateRequest.getLabel() != null) {
            observer.label(observerUpdateRequest.getLabel());
        }
        userRepository.update(user);
    }
}
