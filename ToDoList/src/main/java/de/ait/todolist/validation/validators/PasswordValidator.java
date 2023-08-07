package de.ait.todolist.validation.validators;

import de.ait.todolist.validation.constraints.NotWeakPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PasswordValidator implements ConstraintValidator<NotWeakPassword, String> {

    private static final Pattern pattern = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$");

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        // проверяем строку по шаблону (pattern-у, регулярному выражению)
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
