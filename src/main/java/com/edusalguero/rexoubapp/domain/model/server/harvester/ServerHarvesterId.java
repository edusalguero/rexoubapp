package com.edusalguero.rexoubapp.domain.model.server.harvester;

import com.edusalguero.rexoubapp.domain.shared.UniqueId;
import com.edusalguero.rexoubapp.domain.shared.ValueObject;


public class ServerHarvesterId extends UniqueId implements ValueObject<ServerHarvesterId> {
    protected ServerHarvesterId() {
    }

    public ServerHarvesterId(String string) {
        super(string);
    }

    @Override
    public boolean sameValueAs(ServerHarvesterId other) {
        return other != null && this.id.equals(other.id);
    }
}
