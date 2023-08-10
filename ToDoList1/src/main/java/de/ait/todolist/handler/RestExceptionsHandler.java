package de.ait.todolist.handler;

import de.ait.todolist.dto.ErrorDto;
import de.ait.todolist.exceptions.ForbiddenUpdateUserOperationException;
import de.ait.todolist.exceptions.IncorrectUserIdException;
import de.ait.todolist.exceptions.NotFoundException;
import de.ait.todolist.exceptions.RestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionsHandler {

    @ExceptionHandler(RestException.class) // какой класс ошибок перехватываем
    public ResponseEntity<ErrorDto> handleRestException(RestException e) { // что именно перехватили
        return ResponseEntity // сформировали ответ
                .status(e.getHttpStatus()) // прописываем статус ответа
                .body(ErrorDto.builder() // собираем ответ
                        .message(e.getMessage())
                        .build());
    }
}