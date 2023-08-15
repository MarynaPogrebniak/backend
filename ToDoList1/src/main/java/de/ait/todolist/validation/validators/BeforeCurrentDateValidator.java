package de.ait.todolist.validation.validators;

import de.ait.todolist.dto.NewTaskDto;
import de.ait.todolist.validation.constraints.BeforeCurrentDate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BeforeCurrentDateValidator implements ConstraintValidator<BeforeCurrentDate, NewTaskDto> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Override
    public boolean isValid(NewTaskDto newTaskDto, ConstraintValidatorContext constraintValidatorContext) {

        try {
            LocalDate startDate = LocalDate.parse(newTaskDto.getStartDate());
            LocalDate finishDate = LocalDate.parse(newTaskDto.getFinishDate());
            LocalDate currentDate = LocalDate.now();

            return  startDate.isAfter(currentDate) && finishDate.isAfter(startDate)

                    || startDate.isEqual(currentDate) && finishDate.isAfter(startDate);
        } catch (RuntimeException e) {
            return false;
        }
    }
}