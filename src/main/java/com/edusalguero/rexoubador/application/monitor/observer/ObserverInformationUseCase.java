package com.edusalguero.rexoubador.application.monitor.observer;

import com.edusalguero.rexoubador.domain.model.monitor.observer.ObserverId;
import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserId;
import com.edusalguero.rexoubador.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObserverInformationUseCase {

    @Autowired
    private UserRepository userRepository;

    public ObserverResponse execute(ObserverId observerId, UserId userId) {
        User user = userRepository.ofId(userId);
        return new ObserverResponse(user.observer(observerId));
    }
}
