package com.edusalguero.rexoubador.domain.model.server;

import com.edusalguero.rexoubador.domain.model.monitor.harvester.Harvester;
import com.edusalguero.rexoubador.domain.model.monitor.observer.Observer;
import com.edusalguero.rexoubador.domain.model.server.harvester.ServerHarvester;
import com.edusalguero.rexoubador.domain.model.server.harvester.ServerHarvesterId;
import com.edusalguero.rexoubador.domain.model.server.harvester.ServerHarvesterNotFoundException;
import com.edusalguero.rexoubador.domain.model.server.observer.ServerObserver;
import com.edusalguero.rexoubador.domain.model.server.observer.ServerObserverId;
import com.edusalguero.rexoubador.domain.model.server.observer.ServerObserverNotFoundException;
import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.shared.HarvestStatus;
import com.edusalguero.rexoubador.domain.shared.MachineStatus;
import com.edusalguero.rexoubador.domain.shared.Status;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "server", targetEntity = ServerHarvester.class, orphanRemoval = true)
    private List<ServerHarvester> harvesters = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "server", targetEntity = ServerObserver.class, orphanRemoval = true)
    private List<ServerObserver> observers = new ArrayList<>();


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

    public Collection<ServerHarvester> harvesters()
    {
        return  harvesters;
    }

    public Boolean addHarvester(ServerHarvesterId serverHarvesterId, Harvester harvester)
    {
        ServerHarvester serverHarvester = new ServerHarvester(serverHarvesterId, this, harvester);
        return  harvesters.add(serverHarvester);
    }

    public Boolean deleteHarvester(ServerHarvesterId serverHarvesterId)
    {
       return harvesters.remove(harvester(serverHarvesterId));
    }

    public ServerHarvester harvester(ServerHarvesterId serverHarvesterId) {
        ServerHarvester harvester = harvesters().stream()
                .filter(h ->h.serverHarvesterId().equals(serverHarvesterId))
                .findAny()
                .orElse(null);
        if (harvester == null) {
            throw new ServerHarvesterNotFoundException();
        }
        return harvester;
    }

    public Boolean addObserver(ServerObserverId serverObserverId, Observer observer)
    {
        ServerObserver serverObserver = new ServerObserver(serverObserverId, this, observer);
        return  observers.add(serverObserver);
    }

    public Collection<ServerObserver> observers()
    {
        return  observers;
    }

    public ServerObserver observer(ServerObserverId serverObserverId) {
        ServerObserver observer = observers().stream()
                .filter(o ->o.serverObserverId().equals(serverObserverId))
                .findAny()
                .orElse(null);
        if (observer == null) {
            throw new ServerObserverNotFoundException();
        }
        return observer;
    }

    public Boolean deleteObserver(ServerObserverId serverObserverId)
    {
        return observers.remove(observer(serverObserverId));
    }

    public User user() {
        return user;
    }

    public ServerId serverId() {
        return serverId;
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

    public void updateHarvestDate(Date date)
    {
        this.lastHarvestDate = date;
    }

    public void updateStatus(Status status) {
        this.status = status;
    }

    public void updateUptime(int uptime)
    {
        this.uptime = uptime;
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

    public void delete() {
        status = Status.DELETED;
    }
}
