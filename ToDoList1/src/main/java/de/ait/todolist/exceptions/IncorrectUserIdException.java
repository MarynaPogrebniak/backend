package de.ait.todolist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class IncorrectUserIdException extends RestException {

    public IncorrectUserIdException(Long incorrectId) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, "Id of user <" + incorrectId + "> is incorrect.");
    }
}
