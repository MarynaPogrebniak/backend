package de.ait.todolist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ForbiddenUpdateUserOperationException extends RestException {

    public ForbiddenUpdateUserOperationException(String field, String newValue) {
        super(HttpStatus.FORBIDDEN, "Cannot set <" + field + "> as <" + newValue + ">");
    }

}
