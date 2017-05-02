package com.edusalguero.rexoubador.domain.server;

import com.edusalguero.rexoubador.domain.HarvestStatus;
import com.edusalguero.rexoubador.domain.MachineStatus;
import com.edusalguero.rexoubador.domain.Status;
import com.edusalguero.rexoubador.domain.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "server")
public class Server {

    @EmbeddedId
    private ServerId serverId;

    @Column(name = "label")
    private String label;

    @Column(name = "ip")
    private String ip;

    @Column(name = "uptime")
    private int uptime;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private MachineStatus machineStatus;

    @Enumerated(EnumType.STRING)
    private HarvestStatus harvestStatus;

    @Column(name = "entry_date", columnDefinition = "DATETIME", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date entryDate;

    @Column(name = "last_harvest_date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastHarvestDate;

    @Basic(optional = false)
    @Column(name = "updated_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    protected Server() {
        // Needed by JPA
    }

    public Server(User user, ServerId serverId, String label, String ip, Status status) {
        this.user = user;
        this.serverId = serverId;
        this.label = label;
        this.ip = ip;
        this.status = status;
        this.entryDate = new Date();
        this.harvestStatus = HarvestStatus.PENDING;
        this.machineStatus = MachineStatus.UNKNOWN;
    }

    public User user() {
        return user;
    }

    public ServerId serverId()
    {
        return  serverId;
    }
    public String id() {
        return serverId.toString();
    }

    public String label() {
        return label;
    }

    public String ip() {
        return ip;
    }

    public int uptime() {
        return uptime;
    }

    public Status status() {
        return status;
    }

    public MachineStatus machineStatus() {
        return machineStatus;
    }

    public HarvestStatus harvestStatus() {
        return harvestStatus;
    }

    public Date entryDate() {
        return entryDate;
    }

    public Date lastHarvestDate() {
        return lastHarvestDate;
    }

    public void updateMachineStatus(MachineStatus status) {
        this.machineStatus = status;
    }

    public void updateHarvestStatus(HarvestStatus status) {
        this.harvestStatus = status;
    }

    public void updateStatus(Status status) {
        this.status = status;
    }

    public void updateIp(String ip) {
        this.ip = ip;
    }

    public void updateLabel(String label) {
        this.label = label;
    }

    public Boolean isEnabled() {
        return this.status == Status.ENABLED;
    }

    public Boolean isSoftDeleted() {
        return status == Status.DELETED;
    }

    public void delete()
    {
        status = Status.DELETED;
    }
}
