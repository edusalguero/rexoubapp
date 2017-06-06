package com.edusalguero.rexoubapp.domain.service.executor.command.response;

import com.edusalguero.rexoubapp.domain.model.monitor.MonitorDataInterface;

abstract public class CommandResponse implements CommandResponseInterface {

    protected String type;
    protected Object name;
    private MonitorDataInterface data;

    @Override
    public String getType() {
        return type;
    }

    @Override
    public MonitorDataInterface getData() {
        return data;
    }

    public void setData(MonitorDataInterface data) {
        this.data = data;
    }
}
