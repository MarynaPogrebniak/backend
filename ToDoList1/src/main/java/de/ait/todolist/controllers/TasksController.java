package de.ait.todolist.controllers;

import de.ait.todolist.controllers.api.TasksApi;
import de.ait.todolist.dto.NewTaskDto;
import de.ait.todolist.dto.TaskDto;
import de.ait.todolist.dto.TasksDto;
import de.ait.todolist.dto.UsersDto;
import de.ait.todolist.services.TasksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TasksController implements TasksApi {

    private final TasksService tasksService;

    @Override
    public ResponseEntity<TasksDto> getAllTasks(Integer pageNumber,
                                                String orderBy,
                                                Boolean desc,
                                                String filterBy,
                                                String filterValue) {
        return ResponseEntity
                .ok(tasksService.getAllTasks(pageNumber, orderBy,desc, filterBy, filterValue));
    }
}

