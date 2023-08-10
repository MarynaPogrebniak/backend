package de.ait.todolist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class NotFoundException extends RestException {

    public NotFoundException(String entity, Long id) {
        super(HttpStatus.NOT_FOUND, entity + " with id <" + id + "> not found.");
    }
}
