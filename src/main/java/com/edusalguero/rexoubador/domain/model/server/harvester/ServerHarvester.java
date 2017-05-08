package com.edusalguero.rexoubador.domain.model.server.harvester;

import com.edusalguero.rexoubador.domain.model.monitor.harvester.Harvester;
import com.edusalguero.rexoubador.domain.model.server.Server;

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
        return new Harvest(this.lastHarvestDate, this.lastHarvestData);
    }

    public void addCollectedData(Date harvestDate, String data) {
        this.lastHarvestDate = harvestDate;
        this.lastHarvestData = data;
    }

    public Date entryDate() {
        return entryDate;
    }

}
