package de.ait.todolist.controllers.api;

import de.ait.todolist.dto.NewUserDto;
import de.ait.todolist.dto.TasksDto;
import de.ait.todolist.dto.UserDto;
import de.ait.todolist.dto.UsersDto;
import de.ait.todolist.validation.dto.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tags(value = {
        @Tag(name = "Users")
})
@RequestMapping("/users")
public interface UsersApi {

    @Operation(summary = "Создание пользователя", description = "Доступно только администратору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Пользователь создан",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ValidationErrorsDto.class))
                    })
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<UserDto> addUser(@Parameter(required = true, description = "Пользователь") @RequestBody @Valid NewUserDto newUser);

    @Operation(summary = "Получение всех пользователей", description = "Доступно всем")
    @GetMapping
    ResponseEntity<UsersDto> getAllUsers();


    @Operation(summary = "Получение всех задач пользователя", description = "Доступно всем")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Пользователь не найден",
                    content = {
                            @Content()
                    }),
            @ApiResponse(responseCode = "200", description = "Задачи пользователя",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = TasksDto.class))
                    })
    })
    @GetMapping("/{user-id}/tasks")
    ResponseEntity<TasksDto> getTasksOfUser(@Parameter(required = true, description = "Идентификатор пользователя", example = "2")
                                                  @PathVariable("user-id") Long userId);

}

