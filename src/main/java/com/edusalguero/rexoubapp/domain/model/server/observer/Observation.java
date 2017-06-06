package com.edusalguero.rexoubapp.domain.model.server.observer;


import com.edusalguero.rexoubapp.domain.shared.CheckStatus;

import java.util.Date;

public class Observation {
    private Date date;
    private CheckStatus checkStatus;

    public Observation(Date date, CheckStatus checkStatus) {
        this.date = date;
        this.checkStatus = checkStatus;
    }

    public Date getDate() {
        return date;
    }

    public CheckStatus getCheckStatus() {
        return checkStatus;
    }
}
