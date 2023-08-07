package de.ait.todolist.validation.validators;

import de.ait.todolist.validation.constraints.BeforeCurrentDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BeforeCurrentDateValidator implements ConstraintValidator<BeforeCurrentDate, String> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Override
    public boolean isValid(String checkDate, ConstraintValidatorContext constraintValidatorContext) {

        LocalDate checkDateLD = LocalDate.parse(checkDate, formatter);
        LocalDate currentDate = LocalDate.now();

        return checkDateLD.isEqual(currentDate) || checkDateLD.isAfter(currentDate);
    }
}
