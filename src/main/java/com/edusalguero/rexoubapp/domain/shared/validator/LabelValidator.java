package com.edusalguero.rexoubapp.domain.shared.validator;

public class LabelValidator implements ValidatorInterface<String> {
    @Override
    public boolean validate(String object) {
        return !object.isEmpty();
    }
}
