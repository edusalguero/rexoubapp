package com.edusalguero.rexoubador.application.server;

import com.edusalguero.rexoubador.application.datatransformer.DateConverter;
import com.edusalguero.rexoubador.domain.model.server.Server;
import com.edusalguero.rexoubador.domain.shared.HarvestStatus;
import com.edusalguero.rexoubador.domain.shared.MachineStatus;
import com.edusalguero.rexoubador.domain.shared.Status;

import java.util.Date;


public class ServerSummaryResponse {
    private String id;
    private String label;
    private String ip;
    private Date entryDate;
    private Status status;
    private HarvestStatus harvestStatus;
    private MachineStatus machineStatus;
    private Date lastHarvestDate;
    private int uptime;
    private int observersCount;
    private int harvestersCount;


    public ServerSummaryResponse(Server server) {
        this.id = server.id();
        this.label = server.label();
        this.ip = server.ip();
        this.entryDate = server.entryDate();
        this.status = server.status();
        this.harvestStatus = server.harvestStatus();
        this.machineStatus = server.machineStatus();
        this.lastHarvestDate = server.lastHarvestDate();
        this.uptime = server.uptime();
        this.harvestersCount = server.harvesters().size();
        this.observersCount = server.observers().size();
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getIp() {
        return ip;
    }

    public String getEntryDate() {
        return DateConverter.getFormattedDateOrEmptyString(entryDate);
    }

    public Status getStatus() {
        return status;
    }

    public HarvestStatus getHarvestStatus() {
        return harvestStatus;
    }

    public MachineStatus getMachineStatus() {
        return machineStatus;
    }

    public String getLastHarvestDate() {
        return DateConverter.getFormattedDateOrEmptyString(lastHarvestDate);
    }

    public int getUptime() {
        return uptime;
    }

    public int getHarvestersCount() {
        return harvestersCount;
    }

    public int getObserversCount() {
        return observersCount;
    }
}
