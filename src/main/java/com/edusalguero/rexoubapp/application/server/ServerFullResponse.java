package com.edusalguero.rexoubapp.application.server;

import com.edusalguero.rexoubapp.application.datatransformer.DateConverter;
import com.edusalguero.rexoubapp.application.server.harvester.ServerHarvesterResponse;
import com.edusalguero.rexoubapp.application.server.observer.ServerObserverResponse;
import com.edusalguero.rexoubapp.domain.model.server.Server;
import com.edusalguero.rexoubapp.domain.model.server.harvester.ServerHarvester;
import com.edusalguero.rexoubapp.domain.model.server.observer.ServerObserver;
import com.edusalguero.rexoubapp.domain.shared.HarvestStatus;
import com.edusalguero.rexoubapp.domain.shared.MachineStatus;
import com.edusalguero.rexoubapp.domain.shared.Status;

import java.util.ArrayList;
import java.util.Date;


public class ServerFullResponse {
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
    private ArrayList<ServerHarvesterResponse> harvesters = new ArrayList<>();
    private ArrayList<ServerObserverResponse> observers = new ArrayList<>();

    public ServerFullResponse(Server server) {
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

        for (ServerHarvester harvester : server.harvesters()) {
            harvesters.add(new ServerHarvesterResponse(harvester));
        }

        for (ServerObserver observer : server.observers()) {
            observers.add(new ServerObserverResponse(observer));
        }
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

    public int getUptime() {
        return uptime;
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


    public int getHarvestersCount() {
        return harvestersCount;
    }

    public int getObserversCount() {
        return observersCount;
    }

    public ArrayList<ServerHarvesterResponse> getHarvesters() {
        return harvesters;
    }

    public ArrayList<ServerObserverResponse> getObservers() {
        return observers;
    }
}
