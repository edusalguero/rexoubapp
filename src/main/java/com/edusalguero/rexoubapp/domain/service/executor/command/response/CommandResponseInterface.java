package com.edusalguero.rexoubapp.domain.service.executor.command.response;

import com.edusalguero.rexoubapp.domain.model.monitor.MonitorDataInterface;


public interface CommandResponseInterface {
    Object getName();

    MonitorDataInterface getData();

    String getType();

    void setData(MonitorDataInterface monitorDataInterface);

}
