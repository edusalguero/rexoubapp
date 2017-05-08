package com.edusalguero.rexoubador.infraestructure.spring.controller;

import com.edusalguero.rexoubador.application.monitor.harvester.*;
import com.edusalguero.rexoubador.domain.shared.Status;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.HarvesterId;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.HarvesterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/harvesters", produces = "application/json")
public class HarvesterController extends AuthenticatedUserController {

    @Autowired
    protected HarvesterCreateUseCase harvesterCreateUseCase;

    @Autowired
    protected HarvesterInformationUseCase harvesterInformationUseCase;

    @Autowired
    protected HarvesterUpdateUseCase harvesterUpdateUseCase;

    @Autowired
    protected HarvesterDeleteUseCase harvesterDeleteUseCase;

    @Autowired
    protected UserHarvestersUseCase userHarvestersUseCase;

    @RequestMapping(method = RequestMethod.GET)
    public ArrayList<HarvesterResponse> list() {
        return userHarvestersUseCase.execute(getAuthenticatedUserId());
    }

    @RequestMapping(method = RequestMethod.POST)
    public HarvesterId add(@RequestParam(value = "label") String label,
                           @RequestParam(value = "notifyWarning") Boolean notifyWarning,
                           @RequestParam(value = "notifyAlert") Boolean notifyAlert,
                           @RequestParam(value = "warningValue") String warningValue,
                           @RequestParam(value = "alertValue") String alertValue,
                           @RequestParam(value = "type") HarvesterType type,
                           @RequestParam(value = "status", required = false, defaultValue = "ENABLED") Status status) {

        return harvesterCreateUseCase.execute(getAuthenticatedUserId(), type, label, notifyWarning, notifyAlert, warningValue, alertValue, status);
    }

    @RequestMapping(path = "/{harvesterId}", method = RequestMethod.GET)
    public HarvesterResponse view(@PathVariable String harvesterId) {
        return harvesterInformationUseCase.execute(new HarvesterId(harvesterId), getAuthenticatedUserId());
    }

    @RequestMapping(path = "/{harvesterId}", method = RequestMethod.PUT)
    public void update(@PathVariable String harvesterId,
                       @RequestParam(value = "label") String label,
                       @RequestParam(value = "notifyWarning") Boolean notifyWarning,
                       @RequestParam(value = "notifyAlert") Boolean notifyAlert,
                       @RequestParam(value = "warningValue") String warningValue,
                       @RequestParam(value = "alertValue") String alertValue,
                       @RequestParam(value = "type") HarvesterType type,
                       @RequestParam(value = "status", required = false, defaultValue = "ENABLED") Status status
    ) {
        HarvesterUpdateRequest harvesterUpdateRequest = new HarvesterUpdateRequest(new HarvesterId(harvesterId), getAuthenticatedUserId(), label, notifyWarning, notifyAlert, warningValue, alertValue, status);
        harvesterUpdateUseCase.execute(harvesterUpdateRequest);
    }

    @RequestMapping(path = "/{harvesterId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String harvesterId) {
        harvesterDeleteUseCase.execute(new HarvesterId(harvesterId), getAuthenticatedUserId());
    }

}
