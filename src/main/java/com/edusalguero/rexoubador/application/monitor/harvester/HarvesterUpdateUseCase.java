package com.edusalguero.rexoubador.application.monitor.harvester;

import com.edusalguero.rexoubador.domain.model.monitor.harvester.Harvester;
import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HarvesterUpdateUseCase {
    private final UserRepository userRepository;

    @Autowired
    public HarvesterUpdateUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(HarvesterUpdateRequest harvesterUpdateRequest) {
        User user = userRepository.ofId(harvesterUpdateRequest.getUserId());
        Harvester harvester = user.harvester(harvesterUpdateRequest.getHarvesterId());
        harvester.updateStatus(harvesterUpdateRequest.getStatus());
        harvester.warningValue(harvesterUpdateRequest.getWarningValue());
        harvester.alertValue(harvesterUpdateRequest.getAlertValue());
        harvester.notifyWarning(harvesterUpdateRequest.getNotifyWarning());
        harvester.notifyAlert(harvesterUpdateRequest.getNotifyAlert());
        harvester.label(harvesterUpdateRequest.getLabel());
        userRepository.update(user);
    }
}
