package com.edusalguero.rexoubador.domain.shared.validator;

public interface ValidatorInterface<T> {
    boolean validate(T object);
}
