package com.edusalguero.rexoubapp.domain.shared;

import java.io.Serializable;

public interface Event extends Serializable {
    String forTopic();
}
