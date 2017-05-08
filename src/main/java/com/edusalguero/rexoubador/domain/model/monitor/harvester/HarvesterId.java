package com.edusalguero.rexoubador.domain.model.monitor.harvester;

import com.edusalguero.rexoubador.domain.shared.UniqueId;
import com.edusalguero.rexoubador.domain.shared.ValueObject;

public class HarvesterId extends UniqueId implements ValueObject<HarvesterId> {
    protected HarvesterId() {
    }

    public HarvesterId(String string) {
        super(string);
    }

    @Override
    public boolean sameValueAs(HarvesterId other) {
        return other != null && this.id.equals(other.id);
    }
}
