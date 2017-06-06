package com.edusalguero.rexoubapp.domain.model.server.observer;

import com.edusalguero.rexoubapp.domain.shared.CheckStatus;

public class NullObservation extends Observation {
    public NullObservation() {
        super(null, CheckStatus.NOT_CHECKED);
    }
}
