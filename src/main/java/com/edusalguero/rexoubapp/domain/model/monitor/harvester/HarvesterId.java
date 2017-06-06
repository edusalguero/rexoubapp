package com.edusalguero.rexoubapp.domain.model.monitor.harvester;

import com.edusalguero.rexoubapp.domain.shared.UniqueId;
import com.edusalguero.rexoubapp.domain.shared.ValueObject;

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
