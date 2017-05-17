package com.edusalguero.rexoubador.domain.model.server;

import com.edusalguero.rexoubador.domain.event.ServerWasReconnected;
import com.edusalguero.rexoubador.domain.event.ServerWasUnreachable;
import com.edusalguero.rexoubador.domain.model.contact.Contact;
import com.edusalguero.rexoubador.domain.model.event.Event;
import com.edusalguero.rexoubador.domain.model.event.EventId;
import com.edusalguero.rexoubador.domain.model.monitor.Report;
import com.edusalguero.rexoubador.domain.model.monitor.ReportId;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.Harvester;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.HarvesterType;
import com.edusalguero.rexoubador.domain.model.monitor.harvester.harvest.UptimeHarvest;
import com.edusalguero.rexoubador.domain.model.monitor.observer.Observation;
import com.edusalguero.rexoubador.domain.model.monitor.observer.Observer;
import com.edusalguero.rexoubador.domain.model.server.harvester.ServerHarvester;
import com.edusalguero.rexoubador.domain.model.server.harvester.ServerHarvesterId;
import com.edusalguero.rexoubador.domain.model.server.harvester.ServerHarvesterNotFoundException;
import com.edusalguero.rexoubador.domain.model.server.observer.ServerObserver;
import com.edusalguero.rexoubador.domain.model.server.observer.ServerObserverId;
import com.edusalguero.rexoubador.domain.model.server.observer.ServerObserverNotFoundException;
import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.service.executor.command.response.CommandResponseInterface;
import com.edusalguero.rexoubador.domain.service.executor.command.response.HarvestCommandResponse;
import com.edusalguero.rexoubador.domain.service.executor.command.response.ObserverCommandResponse;
import com.edusalguero.rexoubador.domain.service.executor.command.response.UptimeCommandResponse;
import com.edusalguero.rexoubador.domain.shared.*;

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


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "server", targetEntity = Event.class, orphanRemoval = true)
    private List<Event> events = new ArrayList<>();


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

    public Collection<ServerHarvester> harvesters() {
        return harvesters;
    }


    public Boolean addHarvester(ServerHarvesterId serverHarvesterId, Harvester harvester) {
        ServerHarvester serverHarvester = new ServerHarvester(serverHarvesterId, this, harvester);
        return harvesters.add(serverHarvester);
    }

    public Boolean deleteHarvester(ServerHarvesterId serverHarvesterId) {
        return harvesters.remove(harvester(serverHarvesterId));
    }

    public ServerHarvester harvester(ServerHarvesterId serverHarvesterId) {
        ServerHarvester harvester = harvesters().stream()
                .filter(h -> h.serverHarvesterId().equals(serverHarvesterId))
                .findAny()
                .orElse(null);
        if (harvester == null) {
            throw new ServerHarvesterNotFoundException();
        }
        return harvester;
    }

    public Boolean addObserver(ServerObserverId serverObserverId, Observer observer) {
        ServerObserver serverObserver = new ServerObserver(serverObserverId, this, observer);
        return observers.add(serverObserver);
    }

    public Collection<ServerObserver> observers() {
        return observers;
    }

    public ServerObserver observer(ServerObserverId serverObserverId) {
        ServerObserver observer = observers().stream()
                .filter(o -> o.serverObserverId().equals(serverObserverId))
                .findAny()
                .orElse(null);
        if (observer == null) {
            throw new ServerObserverNotFoundException();
        }
        return observer;
    }

    public Boolean deleteObserver(ServerObserverId serverObserverId) {
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

    public Event recordEvent(EventId eventId, Collection<Contact> contacts, String message) {
        Event event = new Event(eventId, this, contacts, message);
        events.add(event);
        return event;
    }


    public Report packageMonitoredData(ReportId reportId, Collection<CommandResponseInterface> collectedData) {
        Date now = new Date();
        Report report = new Report(reportId, now, this.user(), this);

        if (machineStatus.equals(MachineStatus.DOWN)) {
            EventPublisher.publish(new ServerWasReconnected(serverId(), user.userId()));
        }
        for (CommandResponseInterface result : collectedData) {
            if (result instanceof HarvestCommandResponse) {
                ServerHarvesterId id = (ServerHarvesterId) ((HarvestCommandResponse) result).getId();
                report.addMetric(id, (HarvesterType) result.getName(), result.getData());
                this.harvester(id)
                        .addCollectedData(now, result.getData());
            } else if (result instanceof ObserverCommandResponse) {
                ServerObserverId id = (ServerObserverId) ((ObserverCommandResponse) result).getId();
                CheckStatus checkStatus =  ((Observation)result.getData()).getCheckStatus();
                report.addService(id, (String) result.getName(), checkStatus);
                this.observer(id)
                        .addObservation(now, checkStatus);
            } else if (result instanceof UptimeCommandResponse) {
                Integer uptime = ((UptimeHarvest) result.getData()).getUptime();
                this.uptime = uptime;
                report.setUptime(uptime);
            }
        }
        this.harvestStatus = HarvestStatus.DONE;
        this.lastHarvestDate = new Date();
        this.machineStatus = MachineStatus.UP;
        return report;
    }

    public Date lastHarvestDate() {
        return lastHarvestDate;
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

    public void delete() {
        status = Status.DELETED;
    }

    public void reportMonitorProblem() {
        if (this.machineStatus() == MachineStatus.UP) {
            EventPublisher.publish(new ServerWasUnreachable(serverId(), user.userId()));
        }
        this.harvestStatus = HarvestStatus.CONNECTION_ERROR;
        this.machineStatus = MachineStatus.DOWN;
    }

    public void monitoringStart() {
        this.harvestStatus = HarvestStatus.IN_PROGRESS;
    }
}
