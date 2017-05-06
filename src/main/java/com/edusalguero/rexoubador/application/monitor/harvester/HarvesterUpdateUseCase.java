package com.edusalguero.rexoubador.application.monitor.harvester;

import com.edusalguero.rexoubador.domain.monitor.harvester.Harvester;
import com.edusalguero.rexoubador.domain.user.User;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HarvesterUpdateUseCase {
    @Autowired
    private UserRepositoryJPA userRepository;

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
