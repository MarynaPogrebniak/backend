package de.ait.todolist.controllers.api;

import de.ait.todolist.dto.NewTaskDto;
import de.ait.todolist.dto.TaskDto;
import io.swagger.v3.oas.annotations.Operation;
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
        @Tag(name = "Tasks")
})
@RequestMapping("/users")
public interface TasksApi {

    @Operation(summary = "Создание задачи", description = "Доступно всем пользователям")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "422", description = "Пользователь с указанным ID отсутствует в системе",
                    content = {
                            @Content()
                    }),
            @ApiResponse(responseCode = "201", description = "Добавленная задача",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDto.class))
                    })
    })
    @PostMapping("/{users-id}/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<TaskDto> addTask( @RequestBody @Valid NewTaskDto newTask, @PathVariable("users-id") Long userId);
}

