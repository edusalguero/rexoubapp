package com.edusalguero.rexoubador.domain.model.server.harvester;

import com.edusalguero.rexoubador.domain.event.ServerHarvesterExceededUsageThresholdUsage;
import com.edusalguero.rexoubador.domain.event.ThresholdExceededUsageType;
import com.edusalguero.rexoubador.domain.model.monitor.MonitorDataInterface;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.Harvester;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.harvest.HarvestFactory;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.service.ExceedThresholdUsageService;
import com.edusalguero.rexoubador.domain.model.server.Server;
import com.edusalguero.rexoubador.domain.shared.EventPublisher;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "server_harvester")
public class ServerHarvester {
    @EmbeddedId
    private ServerHarvesterId serverHarvesterId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "server_id")
    private Server server;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "harvester_id")
    private Harvester harvester;

    @Column(name = "last_harvest_date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastHarvestDate;

    @Column(name = "last_harvest_data")
    private String lastHarvestData;

    @Column(name = "entry_date", columnDefinition = "DATETIME", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date entryDate;

    @Basic(optional = false)
    @Column(name = "updated_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public ServerHarvester(ServerHarvesterId serverHarvesterId, Server server, Harvester harvester) {
        this.serverHarvesterId = serverHarvesterId;
        this.server = server;
        this.harvester = harvester;
        this.entryDate = new Date();
    }

    protected ServerHarvester() {
        // Needed by JPA
    }

    public ServerHarvesterId serverHarvesterId() {
        return serverHarvesterId;
    }

    public String id() {
        return serverHarvesterId.toString();
    }

    public Server server() {
        return server;
    }

    public Harvester harvester() {
        return harvester;
    }

    public Harvest getLastHarvest() {
        MonitorDataInterface monitorDataInterface = getMonitorData();
        return new Harvest(lastHarvestDate, monitorDataInterface);
    }

    private MonitorDataInterface getMonitorData() {
        HarvestFactory harvestFactory = new HarvestFactory();
        return harvestFactory.make(harvester(), this.lastHarvestData);
    }

    public void addCollectedData(Date harvestDate, MonitorDataInterface data) {

        if (!lastHarvestData.isEmpty()) {
            ExceedThresholdUsageService thresholdUsageService = new ExceedThresholdUsageService(data, getMonitorData());
            if (thresholdUsageService.exceeded(harvester().alertValue())) {
                EventPublisher.publish(new ServerHarvesterExceededUsageThresholdUsage(server.serverId(), server.user().userId(),
                        serverHarvesterId(), ThresholdExceededUsageType.ALERT, harvester().alertValue()));
            } else if (thresholdUsageService.exceeded(harvester().warningValue())) {
                EventPublisher.publish(new ServerHarvesterExceededUsageThresholdUsage(server.serverId(), server.user().userId(),
                        serverHarvesterId(), ThresholdExceededUsageType.WARNING, harvester().warningValue()));
            }
        }
        this.lastHarvestDate = harvestDate;
        this.lastHarvestData = data.toJson();
    }

    public Boolean notifyWarning() {
        return this.harvester().notifyWarning();
    }

    public Boolean notifyAlert() {
        return this.harvester().notifyAlert();
    }


    public Date entryDate() {
        return entryDate;
    }

}
