package de.aittr.products.validators;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NameValidatorImpl implements NameValidator {

    private final int minLength;

    public NameValidatorImpl(@Value("${min.name.length}") int minLength) {
        this.minLength = minLength;
    }

    @Override
    public void validate(String name) {
        if (name.length() < minLength) {
            throw new IllegalArgumentException("Название слишком короткое");
        }
    }
}
