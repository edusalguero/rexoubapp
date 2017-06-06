package com.edusalguero.rexoubapp.application.report;


import com.edusalguero.rexoubapp.domain.model.monitor.Report;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ServerReportResponse {

    private String id;
    private Date timestamp;
    private HashMap<String, String> server = new HashMap<>();
    private Integer uptime;
    private ArrayList<Object> metrics = new ArrayList<>();
    private HashMap<String, ArrayList> checks;

    ServerReportResponse(Report report) {
        id = report.getId();
        timestamp = report.timestamp();
        server = report.server();
        uptime = report.uptime();
        metrics = report.metrics();
        checks = report.checks();
    }

    public String getId() {
        return id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public HashMap<String, String> getServer() {
        return server;
    }

    public Integer getUptime() {
        return uptime;
    }

    public ArrayList<Object> getMetrics() {
        return metrics;
    }

    public HashMap<String, ArrayList> getChecks() {
        return checks;
    }
}
