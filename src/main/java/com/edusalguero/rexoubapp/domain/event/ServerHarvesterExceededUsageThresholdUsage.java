package com.edusalguero.rexoubapp.domain.event;

import com.edusalguero.rexoubapp.domain.model.server.ServerId;
import com.edusalguero.rexoubapp.domain.model.server.harvester.ServerHarvesterId;
import com.edusalguero.rexoubapp.domain.model.user.UserId;
import com.edusalguero.rexoubapp.domain.shared.DomainEvent;

import java.util.Date;

public class ServerHarvesterExceededUsageThresholdUsage extends DomainEvent {
    private final ServerId serverId;
    private final UserId userId;
    private final ServerHarvesterId serverHarvesterId;
    private final ThresholdExceededUsageType type;
    private String exceededValue;

    public ServerHarvesterExceededUsageThresholdUsage(ServerId serverId, UserId userId, ServerHarvesterId serverHarvesterId, ThresholdExceededUsageType type, String exceededValue) {
        super(new Date());
        this.serverId = serverId;
        this.userId = userId;
        this.serverHarvesterId = serverHarvesterId;
        this.type = type;
        this.exceededValue = exceededValue;
    }

    public String getExceededValue() {
        return exceededValue;
    }

    public ServerId getServerId() {
        return serverId;
    }

    public UserId getUserId() {
        return userId;
    }

    public ServerHarvesterId getServerHarvesterId() {
        return serverHarvesterId;
    }

    public ThresholdExceededUsageType getType() {
        return type;
    }

    @Override
    public String forTopic() {
        return "notificationsQueue";
    }
}
