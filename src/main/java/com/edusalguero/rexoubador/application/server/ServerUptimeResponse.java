package com.edusalguero.rexoubador.application.server;

import com.edusalguero.rexoubador.domain.server.Server;
import com.edusalguero.rexoubador.application.datatransformer.DateConverter;

import java.util.Date;

public class ServerUptimeResponse {
    private String serverId;
    private int uptime;
    private Date harvestDate;

    public ServerUptimeResponse(Server server) {
        this.serverId = server.id();
        this.uptime = server.uptime();
        this.harvestDate = server.lastHarvestDate();
    }

    public String getServerId() {
        return serverId;
    }

    public int getUptime() {
        return uptime;
    }

    public String getHarvestDate() {
        return DateConverter.getFormattedDateOrEmptyString(harvestDate);
    }
}
