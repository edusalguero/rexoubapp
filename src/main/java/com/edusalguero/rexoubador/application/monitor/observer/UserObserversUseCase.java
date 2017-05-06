package com.edusalguero.rexoubador.application.monitor.observer;

import com.edusalguero.rexoubador.domain.monitor.observer.Observer;
import com.edusalguero.rexoubador.domain.user.User;
import com.edusalguero.rexoubador.domain.user.UserId;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserObserversUseCase {
    @Autowired
    private UserRepositoryJPA userRepository;

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
