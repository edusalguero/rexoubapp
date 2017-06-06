package com.edusalguero.rexoubapp.domain.model.monitor;

import com.edusalguero.rexoubapp.domain.shared.UniqueId;
import com.edusalguero.rexoubapp.domain.shared.ValueObject;

public class ReportId extends UniqueId implements ValueObject<ReportId> {
    protected ReportId() {
    }

    public ReportId(String string) {
        super(string);
    }

    @Override
    public boolean sameValueAs(ReportId other) {
        return other != null && this.id.equals(other.id);
    }
}
