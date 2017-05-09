package com.edusalguero.rexoubador.application.monitor.harvester;


import com.edusalguero.rexoubador.domain.model.monitor.harvester.HarvesterId;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.HarvesterRepository;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.HarvesterType;
import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserId;
import com.edusalguero.rexoubador.domain.model.user.UserRepository;
import com.edusalguero.rexoubador.domain.shared.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HarvesterCreateUseCase {

    @Autowired
    private HarvesterRepository harvesterRepository;
    @Autowired
    private UserRepository userRepository;

    public HarvesterId execute(UserId userId, HarvesterType type, String label, Boolean notifyWarning, Boolean notifyAlert, String warningValue, String alertValue, Status status) {
        HarvesterId harvesterId = harvesterRepository.nextIdentity();
        User user = userRepository.ofId(userId);
        user.addHarvester(harvesterId, type, label, notifyWarning, notifyAlert, warningValue, alertValue, status);
        userRepository.update(user);
        return harvesterId;
    }
}
