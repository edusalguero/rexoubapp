package com.edusalguero.rexoubador.domain.model.server.observer;

import com.edusalguero.rexoubador.domain.shared.CheckStatus;

public class NullObservation extends Observation {
    public NullObservation() {
        super(null, CheckStatus.NOT_CHECKED);
    }
}
