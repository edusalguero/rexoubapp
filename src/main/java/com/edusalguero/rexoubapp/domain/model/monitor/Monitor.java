package com.edusalguero.rexoubapp.domain.model.monitor;

import com.edusalguero.rexoubapp.domain.model.user.User;
import com.edusalguero.rexoubapp.domain.shared.Status;
import com.edusalguero.rexoubapp.domain.shared.ValidationException;
import com.edusalguero.rexoubapp.domain.shared.validator.LabelValidator;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
abstract public class Monitor {

    @Column(name = "entry_date", columnDefinition = "DATETIME", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date entryDate;

    @Enumerated(EnumType.STRING)
    protected Status status;

    @Basic(optional = false)
    @Column(name = "updated_at", insertable = false, updatable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    protected User user;

    @Column(name = "label")
    protected String label;

    protected void setLabel(String label) {
        LabelValidator labelValidator = new LabelValidator();
        if(!labelValidator.validate(label))
        {
            throw new ValidationException("Invalid label.");
        }
        this.label = label;
    }

    public User user() {
        return user;
    }

    public void disable() {
        status = Status.DISABLED;
    }

    public void enable() {
        status = Status.ENABLED;
    }

    public Date entryDate() {
        return entryDate;
    }

    public Status status() {
        return status;
    }

    public Boolean isSoftDeleted() {
        return status == Status.DELETED;
    }

    public void delete() {
        status = Status.DELETED;
    }

    public String label() {
        return label;
    }

    public void label(String label) {
        setLabel(label);
    }
}
