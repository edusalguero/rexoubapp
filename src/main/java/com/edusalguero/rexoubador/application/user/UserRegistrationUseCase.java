package com.edusalguero.rexoubador.application.user;

import com.edusalguero.rexoubador.domain.model.monitor.harvester.HarvesterRepository;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.HarvesterType;
import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserId;
import com.edusalguero.rexoubador.domain.model.user.UserRepository;
import com.edusalguero.rexoubador.domain.model.user.service.HashingService;
import com.edusalguero.rexoubador.domain.shared.Status;
import com.edusalguero.rexoubador.domain.shared.ValidationException;
import com.edusalguero.rexoubador.domain.shared.validator.UsernameUniqueValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationUseCase {

    private final UserRepository userRepository;
    private final HarvesterRepository harvesterRepository;
    private final HashingService hashingService;
    private final UsernameUniqueValidator usernameUniqueValidator;

    @Autowired
    public UserRegistrationUseCase(UserRepository userRepository, HarvesterRepository harvesterRepository, HashingService hashingService) {
        this.userRepository = userRepository;
        this.harvesterRepository = harvesterRepository;
        this.hashingService = hashingService;
        this.usernameUniqueValidator = new UsernameUniqueValidator(this.userRepository);
    }

    public UserId execute(String username, String password, String firstName, String lastName) {
        UserId uid = userRepository.nextIdentity();

        if (!usernameUniqueValidator.validate(username)) {
            throw new ValidationException("Invalid username! Select a different one");
        }
        User u = new User(uid, username, password, firstName, lastName, hashingService);
        setupDefaultMonitors(u);
        userRepository.register(u);
        return uid;
    }

    private void setupDefaultMonitors(User user) {
        user.addHarvester(harvesterRepository.nextIdentity(), HarvesterType.DISK_USAGE,
                "Disk usage", true, true,
                "90", "95", Status.ENABLED);

        user.addHarvester(harvesterRepository.nextIdentity(), HarvesterType.LOAD,
                "CPU load", true, true,
                "90", "95", Status.ENABLED);

        user.addHarvester(harvesterRepository.nextIdentity(), HarvesterType.MEMORY_USAGE,
                "Memory usage", true, true,
                "90", "95", Status.ENABLED);

    }


}
