package com.edusalguero.rexoubador.domain.server.observer;


import com.edusalguero.rexoubador.application.datatransformer.DateConverter;
import com.edusalguero.rexoubador.domain.CheckStatus;

import java.util.Date;

public class Observation {
    private Date date;
    private CheckStatus checkStatus;

    public Observation(Date date, CheckStatus checkStatus) {
        this.date = date;
        this.checkStatus = checkStatus;
    }

    public String getDate() {
        return DateConverter.getFormattedDateOrEmptyString(date);
    }

    public CheckStatus getCheckStatus() {
        return checkStatus;
    }
}
