package com.edusalguero.rexoubador.domain.model.monitor;

import com.edusalguero.rexoubador.domain.model.monitor.harvester.HarvesterType;
import com.edusalguero.rexoubador.domain.model.server.Server;
import com.edusalguero.rexoubador.domain.model.server.harvester.ServerHarvesterId;
import com.edusalguero.rexoubador.domain.model.server.observer.ServerObserverId;
import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.shared.CheckStatus;
import com.google.gson.Gson;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@Document(collection = "reports")
public class Report {
    @Id

    private String id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date timestamp;
    private HashMap<String, String> user = new HashMap<String, String>();
    private HashMap<String, String> server = new HashMap<String, String>();
    private Integer uptime;
    private ArrayList<Object> metrics = new ArrayList<Object>();
    private HashMap<String, ArrayList> checks = new HashMap<String, ArrayList>();

    protected Report() {
        // Needs by Mongo
    }

    public Report(ReportId id, Date timestamp, User user, Server server) {
        this.id = id.getId();
        this.timestamp = timestamp;
        this.user.put("id", user.id());
        this.server.put("id", server.id());
        this.server.put("ip", server.ip());
        this.uptime = 0;
    }

    public String getId() {
        return id;
    }

    public Date timestamp() {
        return timestamp;
    }

    public HashMap<String, String> user() {
        return user;
    }

    public HashMap<String, String> server() {
        return server;
    }

    public Integer uptime() {
        return uptime;
    }

    public void setUptime(Integer uptime) {
        this.uptime = uptime;
    }

    public ArrayList<Object> metrics() {
        return metrics;
    }

    public HashMap<String, ArrayList> checks() {
        return checks;
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
