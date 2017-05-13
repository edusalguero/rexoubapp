package com.edusalguero.rexoubador.application.report;


import com.edusalguero.rexoubador.domain.model.monitor.Report;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ServerReportResponse {

    private String id;
    private Date timestamp;
    private HashMap<String, String> server = new HashMap<String, String>();
    private Integer uptime;
    private ArrayList<Object> metrics = new ArrayList<Object>();
    private HashMap<String, ArrayList> checks = new HashMap<String, ArrayList>();

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
