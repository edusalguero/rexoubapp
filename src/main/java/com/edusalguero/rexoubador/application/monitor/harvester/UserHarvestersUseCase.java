package com.edusalguero.rexoubador.application.monitor.harvester;

import com.edusalguero.rexoubador.domain.monitor.harvester.Harvester;
import com.edusalguero.rexoubador.domain.user.User;
import com.edusalguero.rexoubador.domain.user.UserId;
import com.edusalguero.rexoubador.infraestructure.persistence.jpa.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserHarvestersUseCase {
    @Autowired
    private UserRepositoryJPA userRepository;

    public ArrayList<HarvesterResponse> execute(UserId userId) {
        ArrayList<HarvesterResponse> userHarvesters = new ArrayList<>();
        User user = userRepository.ofId(userId);
        List<Harvester> harvesters = user.harvesters();
        for (Harvester harvester : harvesters) {
            userHarvesters.add(new HarvesterResponse(harvester));
        }
        return userHarvesters;
    }
}
