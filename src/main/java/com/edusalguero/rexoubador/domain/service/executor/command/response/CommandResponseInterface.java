package com.edusalguero.rexoubador.domain.service.executor.command.response;

import com.edusalguero.rexoubador.domain.model.monitor.MonitorDataInterface;


public interface CommandResponseInterface {
    Object getName();

    MonitorDataInterface getData();

    String getType();

    void setData(MonitorDataInterface monitorDataInterface);

}
