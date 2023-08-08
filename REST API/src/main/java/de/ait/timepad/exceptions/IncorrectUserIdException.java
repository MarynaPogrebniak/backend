package de.ait.timepad.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class IncorrectUserIdException extends RuntimeException {

    private Long id;

    public IncorrectUserIdException(Long incorrectId) {
        super();
        this.id = incorrectId;
    }

    public Long getId() {
        return id;
    }
}