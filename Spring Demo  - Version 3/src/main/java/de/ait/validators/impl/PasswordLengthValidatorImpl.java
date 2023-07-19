package de.ait.validators.impl;

import de.ait.validators.PasswordValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PasswordLengthValidatorImpl implements PasswordValidator {

    private final int minLength;

    public PasswordLengthValidatorImpl(@Value("${min.password.length}") int minLength) {
        this.minLength = minLength;
    }

    @Override
    public void validate(String password) {
        if (password.length() < minLength) {
            throw new IllegalArgumentException("Пароль слишком короткий");
        }
    }
}
