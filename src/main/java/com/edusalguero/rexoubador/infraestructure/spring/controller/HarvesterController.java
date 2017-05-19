package com.edusalguero.rexoubador.infraestructure.spring.controller;

import com.edusalguero.rexoubador.application.monitor.harvester.*;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.HarvesterId;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.HarvesterType;
import com.edusalguero.rexoubador.domain.shared.Status;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/v1/harvesters", produces = "application/json")
@Api(description="Authenticated user harvesters management operations")
public class HarvesterController extends AuthenticatedUserController {

    private final HarvesterCreateUseCase harvesterCreateUseCase;

    private final HarvesterInformationUseCase harvesterInformationUseCase;

    private final HarvesterUpdateUseCase harvesterUpdateUseCase;

    private final HarvesterDeleteUseCase harvesterDeleteUseCase;

    private final UserHarvestersUseCase userHarvestersUseCase;

    @Autowired
    public HarvesterController(HarvesterCreateUseCase harvesterCreateUseCase, HarvesterInformationUseCase harvesterInformationUseCase, HarvesterUpdateUseCase harvesterUpdateUseCase, HarvesterDeleteUseCase harvesterDeleteUseCase, UserHarvestersUseCase userHarvestersUseCase) {
        this.harvesterCreateUseCase = harvesterCreateUseCase;
        this.harvesterInformationUseCase = harvesterInformationUseCase;
        this.harvesterUpdateUseCase = harvesterUpdateUseCase;
        this.harvesterDeleteUseCase = harvesterDeleteUseCase;
        this.userHarvestersUseCase = userHarvestersUseCase;
    }

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
