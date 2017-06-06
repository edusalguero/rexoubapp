package com.edusalguero.rexoubapp.application.monitor.harvester;


import com.edusalguero.rexoubapp.domain.model.monitor.harvester.HarvesterId;
import com.edusalguero.rexoubapp.domain.model.monitor.harvester.HarvesterRepository;
import com.edusalguero.rexoubapp.domain.model.monitor.harvester.HarvesterType;
import com.edusalguero.rexoubapp.domain.model.user.User;
import com.edusalguero.rexoubapp.domain.model.user.UserId;
import com.edusalguero.rexoubapp.domain.model.user.UserRepository;
import com.edusalguero.rexoubapp.domain.shared.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HarvesterCreateUseCase {

    private final HarvesterRepository harvesterRepository;
    private final UserRepository userRepository;

    @Autowired
    public HarvesterCreateUseCase(HarvesterRepository harvesterRepository, UserRepository userRepository) {
        this.harvesterRepository = harvesterRepository;
        this.userRepository = userRepository;
    }

    public HarvesterId execute(UserId userId, HarvesterType type, String label, Boolean notifyWarning, Boolean notifyAlert, String warningValue, String alertValue, Status status) {
        HarvesterId harvesterId = harvesterRepository.nextIdentity();
        User user = userRepository.ofId(userId);
        user.addHarvester(harvesterId, type, label, notifyWarning, notifyAlert, warningValue, alertValue, status);
        userRepository.update(user);
        return harvesterId;
    }
}
