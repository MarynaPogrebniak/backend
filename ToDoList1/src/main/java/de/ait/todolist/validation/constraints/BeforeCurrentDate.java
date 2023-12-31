package de.ait.todolist.validation.constraints;

import de.ait.todolist.validation.validators.BeforeCurrentDateValidator;
import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // на что ее можно вешать - на поле
@Retention(RetentionPolicy.RUNTIME) // что аннотация не будет удалена в момент работы приложения
@Constraint(validatedBy = BeforeCurrentDateValidator.class) // чем валидируется
public @interface BeforeCurrentDate {

    String message() default "Start date must be earlier than finish date and they must be later than current date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}