package com.edusalguero.rexoubapp.domain.shared;

import java.io.Serializable;

public interface ValueObject<T> extends Serializable {

    boolean sameValueAs(T other);

    int hashCode();

    boolean equals(Object o);
}