package com.edusalguero.rexoubador.domain.shared.validator;

public class ObserverNameValidator implements ValidatorInterface<String> {
    @Override
    public boolean validate(String object) {
        return !object.isEmpty();
    }
}
