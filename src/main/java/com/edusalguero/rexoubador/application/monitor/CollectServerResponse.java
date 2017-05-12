package com.edusalguero.rexoubador.application.monitor;


import com.edusalguero.rexoubador.domain.model.monitor.harvester.HarvesterType;
import com.edusalguero.rexoubador.domain.model.server.Server;
import com.edusalguero.rexoubador.domain.model.server.harvester.ServerHarvesterId;
import com.edusalguero.rexoubador.domain.model.server.observer.ServerObserverId;
import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.shared.CheckStatus;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CollectServerResponse {

    private Date timestamp;
    private HashMap<String, String> user = new HashMap<String, String>();
    private HashMap<String, String> server = new HashMap<String, String>();
    private Integer uptime;
    private ArrayList<Object> metrics = new ArrayList<Object>();
    private HashMap<String, ArrayList> checks = new HashMap<String, ArrayList>();

    public CollectServerResponse(Date timestamp, User user, Server server) {
        this.timestamp = timestamp;
        this.user.put("id", user.id());
        this.server.put("id", server.id());
        this.server.put("ip", server.ip());
        this.uptime = 0;
    }

    public void setUptime(Integer uptime) {
        this.uptime = uptime;
    }

    public void addMetric(ServerHarvesterId serverHarvesterId, HarvesterType type, Object data) {
        HashMap<String, Object> metric = new HashMap<String, Object>();
        metric.put("id", serverHarvesterId.getId());
        metric.put("type", type);
        metric.put("data", data);
        metrics.add(metric);
    }

    public void addService(ServerObserverId serverObserverId, String serviceName, CheckStatus status) {
        HashMap<String, String> check = new HashMap<String, String>();
        check.put("id", serverObserverId.getId());
        check.put("name", serviceName);
        check.put("status", status.toString());
        boolean services = checks.containsKey("services");
        if (!services) {
            checks.put("services", new ArrayList());
        }
        checks.get("services").add(check);
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }


}
