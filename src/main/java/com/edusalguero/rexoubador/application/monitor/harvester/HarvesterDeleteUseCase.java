package com.edusalguero.rexoubador.application.monitor.harvester;

import com.edusalguero.rexoubador.domain.model.monitor.harvester.HarvesterId;
import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserId;
import com.edusalguero.rexoubador.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HarvesterDeleteUseCase {

    @Autowired
    private UserRepository userRepository;

    public void execute(HarvesterId harvesterId, UserId userId) {
        User user = userRepository.ofId(userId);
        user.deleteHarvester(harvesterId);
        userRepository.update(user);
    }
}
