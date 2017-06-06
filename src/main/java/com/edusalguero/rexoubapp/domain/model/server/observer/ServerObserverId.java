package com.edusalguero.rexoubapp.domain.model.server.observer;

import com.edusalguero.rexoubapp.domain.shared.UniqueId;
import com.edusalguero.rexoubapp.domain.shared.ValueObject;


public class ServerObserverId extends UniqueId implements ValueObject<ServerObserverId> {
    protected ServerObserverId() {
    }

    public ServerObserverId(String string) {
        super(string);
    }

    @Override
    public boolean sameValueAs(ServerObserverId other) {
        return other != null && this.id.equals(other.id);
    }
}
