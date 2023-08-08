package de.ait.todolist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenUpdateUserOperationException extends RuntimeException {

    private String field;
    private String newValue;

    public ForbiddenUpdateUserOperationException(String field, String newValue) {
        super();
        this.field = field;
        this.newValue = newValue;
    }

    public String getField() {
        return field;
    }

    public String getNewValue() {
        return newValue;
    }
}