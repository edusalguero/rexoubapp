package com.edusalguero.rexoubapp.application.monitor.harvester;

import com.edusalguero.rexoubapp.domain.model.monitor.harvester.Harvester;
import com.edusalguero.rexoubapp.domain.model.user.User;
import com.edusalguero.rexoubapp.domain.model.user.UserId;
import com.edusalguero.rexoubapp.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserHarvestersUseCase {
    private final UserRepository userRepository;

    @Autowired
    public UserHarvestersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
