package com.edusalguero.rexoubapp.domain.model.monitor.observer;

import com.edusalguero.rexoubapp.domain.model.monitor.MonitorDataInterface;
import com.edusalguero.rexoubapp.domain.shared.CheckStatus;

public class Observation implements MonitorDataInterface {

    public CheckStatus getCheckStatus() {
        return checkStatus;
    }

    private CheckStatus checkStatus;

    public Observation(CheckStatus checkStatus) {
        this.checkStatus = checkStatus;
    }

    @Override
    public String toJson() {
        return checkStatus.toString();
    }
}
