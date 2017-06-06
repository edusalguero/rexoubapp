package com.edusalguero.rexoubapp.application.monitor.harvester;

import com.edusalguero.rexoubapp.domain.model.monitor.harvester.HarvesterId;
import com.edusalguero.rexoubapp.domain.model.user.User;
import com.edusalguero.rexoubapp.domain.model.user.UserId;
import com.edusalguero.rexoubapp.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HarvesterInformationUseCase {

    private final UserRepository userRepository;

    @Autowired
    public HarvesterInformationUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public HarvesterResponse execute(HarvesterId harvesterId, UserId userId) {
        User user = userRepository.ofId(userId);
        return new HarvesterResponse(user.harvester(harvesterId));
    }
}
