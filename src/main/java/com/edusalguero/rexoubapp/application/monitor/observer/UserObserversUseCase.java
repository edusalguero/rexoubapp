package com.edusalguero.rexoubapp.application.monitor.observer;

import com.edusalguero.rexoubapp.domain.model.monitor.observer.Observer;
import com.edusalguero.rexoubapp.domain.model.user.User;
import com.edusalguero.rexoubapp.domain.model.user.UserId;
import com.edusalguero.rexoubapp.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserObserversUseCase {
    private final UserRepository userRepository;

    @Autowired
    public UserObserversUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ArrayList<ObserverResponse> execute(UserId userId) {
        ArrayList<ObserverResponse> userObservers = new ArrayList<>();
        User user = userRepository.ofId(userId);
        List<Observer> observers = user.observers();
        for (Observer observer : observers) {
            userObservers.add(new ObserverResponse(observer));
        }
        return userObservers;
    }
}
