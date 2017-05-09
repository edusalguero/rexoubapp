package com.edusalguero.rexoubador.application.monitor.harvester;

import com.edusalguero.rexoubador.domain.model.monitor.harvester.HarvesterId;
import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserId;
import com.edusalguero.rexoubador.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HarvesterInformationUseCase {

    @Autowired
    private UserRepository userRepository;

    public HarvesterResponse execute(HarvesterId harvesterId, UserId userId) {
        User user = userRepository.ofId(userId);
        return new HarvesterResponse(user.harvester(harvesterId));
    }
}
