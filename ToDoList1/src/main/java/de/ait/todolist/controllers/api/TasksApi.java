package de.ait.todolist.controllers.api;

import de.ait.todolist.dto.*;
import de.ait.todolist.dto.pages.TasksDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Tags(value = {
        @Tag(name = "Tasks")
})
@RequestMapping("/api/tasks")
public interface TasksApi {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список задач",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = TasksDto.class))
                    }),
            @ApiResponse(responseCode = "403", description = "Попытка сортировки по запрещенному полю",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
                    })
    })
    @Operation(summary = "Получение всех задач", description = "Доступно всем")
    @GetMapping
    ResponseEntity<TasksDto> getAllTasks(
            @Parameter(description = "Номер страницы", example = "1")
            @RequestParam(value = "page") Integer page,
            @Parameter(description = "Поле, по которому хотим выполнять сортировку. Доступно: title, description, startDate, finishDate")
            @RequestParam(value = "orderBy", required = false) String orderBy,
            @Parameter(description = "Указать true, если необходимо сортировать в обратном порядке")
            @RequestParam(value = "desc", required = false) Boolean desc,
            @RequestParam(value = "filterBy", required = false) String filterBy,
            @RequestParam(value = "filterValue", required = false) String filterValue);}

