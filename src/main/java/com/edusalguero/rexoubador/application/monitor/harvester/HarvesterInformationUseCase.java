package com.edusalguero.rexoubador.application.monitor.harvester;

import com.edusalguero.rexoubador.domain.monitor.harvester.HarvesterId;
import com.edusalguero.rexoubador.domain.user.User;
import com.edusalguero.rexoubador.domain.user.UserId;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HarvesterInformationUseCase {

    @Autowired
    private UserRepositoryJPA userRepository;

    public HarvesterResponse execute(HarvesterId harvesterId, UserId userId) {
        User user = userRepository.ofId(userId);
        return new HarvesterResponse(user.harvester(harvesterId));
    }
}
