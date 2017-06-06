package com.edusalguero.rexoubapp.domain.shared.validator;

public interface ValidatorInterface<T> {
    boolean validate(T object);
}
