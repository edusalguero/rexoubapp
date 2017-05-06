package com.edusalguero.rexoubador.infraestructure.spring.controller;

import com.edusalguero.rexoubador.application.observer.*;
import com.edusalguero.rexoubador.domain.Status;
import com.edusalguero.rexoubador.domain.observer.ObserverId;
import com.edusalguero.rexoubador.domain.observer.ObserverType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/observers", produces = "application/json")
public class ObserverController extends AuthenticatedUserController {

    @Autowired
    protected ObserverCreateUseCase observerCreateUseCase;

    @Autowired
    protected ObserverInformationUseCase observerInformationUseCase;

    @Autowired
    protected ObserverUpdateUseCase observerUpdateUseCase;

    @Autowired
    protected ObserverDeleteUseCase observerDeleteUseCase;

    @Autowired
    protected UserObserversUseCase userObserversUseCase;

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

        return observerCreateUseCase.execute(getAuthenticatedUserId(), observerType, name, label, notifyStatusChanges, notifyInactivity);
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
