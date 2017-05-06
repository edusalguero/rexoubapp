package com.edusalguero.rexoubador.application.monitor.harvester;


import com.edusalguero.rexoubador.domain.Status;
import com.edusalguero.rexoubador.domain.monitor.harvester.HarvesterId;
import com.edusalguero.rexoubador.domain.monitor.harvester.HarvesterType;
import com.edusalguero.rexoubador.domain.user.User;
import com.edusalguero.rexoubador.domain.user.UserId;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.HarvesterRepositoryJPA;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HarvesterCreateUseCase {

    @Autowired
    private HarvesterRepositoryJPA harvesterRepository;
    @Autowired
    private UserRepositoryJPA userRepository;

    public HarvesterId execute(UserId userId, HarvesterType type, String label, Boolean notifyWarning, Boolean notifyAlert, String warningValue, String alertValue, Status status) {
        HarvesterId harvesterId = harvesterRepository.nextIdentity();
        User user = userRepository.ofId(userId);
        user.addHarvester(harvesterId,  type,  label,  notifyWarning,  notifyAlert,  warningValue,  alertValue, status);
        userRepository.update(user);
        return harvesterId;
    }
}
