package com.edusalguero.rexoubapp.application.monitor.observer;

import com.edusalguero.rexoubapp.domain.model.monitor.observer.ObserverId;
import com.edusalguero.rexoubapp.domain.model.user.User;
import com.edusalguero.rexoubapp.domain.model.user.UserId;
import com.edusalguero.rexoubapp.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObserverDeleteUseCase {

    private final UserRepository userRepository;

    @Autowired
    public ObserverDeleteUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(ObserverId observerId, UserId userId) {
        User user = userRepository.ofId(userId);
        user.deleteObserver(observerId);
        userRepository.update(user);
    }
}
