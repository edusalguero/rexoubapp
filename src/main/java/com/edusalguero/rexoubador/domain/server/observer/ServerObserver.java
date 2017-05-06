package com.edusalguero.rexoubador.domain.server.observer;

import com.edusalguero.rexoubador.domain.CheckStatus;
import com.edusalguero.rexoubador.domain.monitor.observer.Observer;
import com.edusalguero.rexoubador.domain.server.Server;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "server_observer")
public class ServerObserver {
    @EmbeddedId
    private ServerObserverId serverObserverId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "server_id")
    private Server server;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "observer_id")
    private Observer observer;

    @Column(name = "last_check_date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastCheckDate;

    @Column(name = "last_check_status")
    private CheckStatus lastCheckStatus;

    @Column(name = "entry_date", columnDefinition = "DATETIME", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date entryDate;

    @Basic(optional = false)
    @Column(name = "updated_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public ServerObserver(ServerObserverId serverObserverId, Server server, Observer observer) {
        this.serverObserverId = serverObserverId;
        this.server = server;
        this.observer = observer;
        this.entryDate = new Date();
    }

    protected ServerObserver() {
        // Needed by JPA
    }

    public ServerObserverId serverObserverId() {
        return serverObserverId;
    }

    public String id() {
        return serverObserverId.toString();
    }

    public Server server() {
        return server;
    }

    public Observer observer() {
        return observer;
    }

    public Observation getLastObservation() {
        return new Observation(this.lastCheckDate, this.lastCheckStatus);
    }

    public void addObservation(Date observationDate, CheckStatus checkStatus) {
        this.lastCheckDate = observationDate;
        this.lastCheckStatus = checkStatus;
    }

    public Date entryDate() {
        return entryDate;
    }

}
