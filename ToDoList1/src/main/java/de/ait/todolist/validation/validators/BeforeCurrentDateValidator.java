package de.ait.todolist.validation.validators;

import de.ait.todolist.validation.constraints.BeforeCurrentDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BeforeCurrentDateValidator implements ConstraintValidator<BeforeCurrentDate, String> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Override
    public boolean isValid(String checkDate, ConstraintValidatorContext constraintValidatorContext) {
        if (checkDate == null || checkDate.trim().isEmpty()) {
            return true;
        }

        LocalDate checkDateLD;
        try {
            checkDateLD = LocalDate.parse(checkDate, formatter);
        } catch (RuntimeException e) {
            return false;
        }

        LocalDate currentDate = LocalDate.now();

        return checkDateLD.isEqual(currentDate) || checkDateLD.isAfter(currentDate);
    }
}