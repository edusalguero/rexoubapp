package com.edusalguero.rexoubapp.application.server;

import com.edusalguero.rexoubapp.application.datatransformer.DateConverter;
import com.edusalguero.rexoubapp.domain.model.server.Server;

import java.util.Date;
import java.util.HashMap;

public class ServerUptimeResponse {
    private int uptime;
    private Date harvestDate;
    private HashMap<String, String> server = new HashMap<>();

    public ServerUptimeResponse(Server server) {
        this.server.put("label",server.label());
        this.server.put("id",server.id());
        this.uptime = server.uptime();
        this.harvestDate = server.lastHarvestDate();
    }

    public HashMap<String, String> getServer() {
        return server;
    }

    public int getUptime() {
        return uptime;
    }

    public String getHarvestDate() {
        return DateConverter.getFormattedDateOrEmptyString(harvestDate);
    }
}
