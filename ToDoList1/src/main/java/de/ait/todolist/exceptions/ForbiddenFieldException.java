package de.ait.todolist.exceptions;

import org.springframework.http.HttpStatus;

public class ForbiddenFieldException extends RestException {

    public ForbiddenFieldException(String field) {
        super(HttpStatus.FORBIDDEN, "Cannot work with <" + field + ">");
    }

}
