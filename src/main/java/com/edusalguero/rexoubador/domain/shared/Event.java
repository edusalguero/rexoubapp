package com.edusalguero.rexoubador.domain.shared;

import java.io.Serializable;

public interface Event extends Serializable {
    String forTopic();
}
