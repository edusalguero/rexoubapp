package com.edusalguero.rexoubador.application.monitor.harvester;

import com.edusalguero.rexoubador.domain.model.monitor.harvester.Harvester;
import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserRepository;
import com.edusalguero.rexoubador.domain.shared.Status;
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

        if (harvesterUpdateRequest.getStatus() == Status.DISABLED) {
            harvester.disable();
        } else {
            harvester.enable();
        }

        if (harvesterUpdateRequest.getNotifyAlert() != null) {
            harvester.notifyAlert(harvesterUpdateRequest.getNotifyAlert());
        }

        if (harvesterUpdateRequest.getNotifyWarning() != null) {
            harvester.notifyWarning(harvesterUpdateRequest.getNotifyWarning());
        }

        if (harvesterUpdateRequest.getAlertValue() != null) {
            harvester.alertValue(harvesterUpdateRequest.getAlertValue());
        }

        if (harvesterUpdateRequest.getWarningValue() != null) {
            harvester.warningValue(harvesterUpdateRequest.getWarningValue());
        }
        if (harvesterUpdateRequest.getLabel() != null) {
            harvester.label(harvesterUpdateRequest.getLabel());
        }
        userRepository.update(user);
    }
}
