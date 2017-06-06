package com.edusalguero.rexoubapp.application.monitor.harvester;

import com.edusalguero.rexoubapp.domain.model.monitor.harvester.HarvesterId;
import com.edusalguero.rexoubapp.domain.model.user.User;
import com.edusalguero.rexoubapp.domain.model.user.UserId;
import com.edusalguero.rexoubapp.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HarvesterDeleteUseCase {

    private final UserRepository userRepository;

    @Autowired
    public HarvesterDeleteUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(HarvesterId harvesterId, UserId userId) {
        User user = userRepository.ofId(userId);
        user.deleteHarvester(harvesterId);
        userRepository.update(user);
    }
}
