package com.edusalguero.rexoubador.infraestructure.spring.controller;

import com.edusalguero.rexoubador.application.monitor.observer.*;
import com.edusalguero.rexoubador.domain.model.monitor.observer.ObserverId;
import com.edusalguero.rexoubador.domain.model.monitor.observer.ObserverType;
import com.edusalguero.rexoubador.domain.shared.Status;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/v1/observers", produces = "application/json")
@Api(description="Authenticated user observers management operations")
public class ObserverController extends AuthenticatedUserController {

    private final ObserverCreateUseCase observerCreateUseCase;

    private final ObserverInformationUseCase observerInformationUseCase;

    private final ObserverUpdateUseCase observerUpdateUseCase;

    private final ObserverDeleteUseCase observerDeleteUseCase;

    private final UserObserversUseCase userObserversUseCase;

    @Autowired
    public ObserverController(ObserverCreateUseCase observerCreateUseCase, ObserverInformationUseCase observerInformationUseCase, ObserverUpdateUseCase observerUpdateUseCase, ObserverDeleteUseCase observerDeleteUseCase, UserObserversUseCase userObserversUseCase) {
        this.observerCreateUseCase = observerCreateUseCase;
        this.observerInformationUseCase = observerInformationUseCase;
        this.observerUpdateUseCase = observerUpdateUseCase;
        this.observerDeleteUseCase = observerDeleteUseCase;
        this.userObserversUseCase = userObserversUseCase;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ArrayList<ObserverResponse> list() {
        return userObserversUseCase.execute(getAuthenticatedUserId());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ObserverId add(@RequestParam(value = "name") String name,
                          @RequestParam(value = "label") String label,
                          @RequestParam(value = "notifyStatusChanges") Boolean notifyStatusChanges,
                          @RequestParam(value = "notifyInactivity") Boolean notifyInactivity,
                          @RequestParam(value = "type", required = false, defaultValue = "SERVICE") ObserverType observerType,
                          @RequestParam(value = "status", required = false, defaultValue = "ENABLED") Status status) {

        return observerCreateUseCase.execute(getAuthenticatedUserId(), observerType, name, label, notifyStatusChanges, notifyInactivity, status);
    }

    @RequestMapping(path = "/{observerId}", method = RequestMethod.GET)
    public ObserverResponse view(@PathVariable String observerId) {
        return observerInformationUseCase.execute(new ObserverId(observerId), getAuthenticatedUserId());
    }

    @RequestMapping(path = "/{observerId}", method = RequestMethod.PUT)
    public void update(@PathVariable String observerId,
                       @RequestParam(value = "label") String label,
                       @RequestParam(value = "notifyStatusChanges") Boolean notifyStatusChanges,
                       @RequestParam(value = "notifyInactivity") Boolean notifyInactivity,
                       @RequestParam(value = "status", required = false, defaultValue = "ENABLED") Status status
    ) {
        ObserverUpdateRequest observerUpdateRequest = new ObserverUpdateRequest(new ObserverId(observerId), getAuthenticatedUserId(), label, notifyStatusChanges, notifyInactivity, status);
        observerUpdateUseCase.execute(observerUpdateRequest);
    }

    @RequestMapping(path = "/{observerId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String observerId) {
        observerDeleteUseCase.execute(new ObserverId(observerId), getAuthenticatedUserId());
    }

}
