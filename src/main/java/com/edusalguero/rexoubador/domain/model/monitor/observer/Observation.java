package com.edusalguero.rexoubador.domain.model.monitor.observer;

import com.edusalguero.rexoubador.domain.model.monitor.MonitorDataInterface;
import com.edusalguero.rexoubador.domain.shared.CheckStatus;

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
