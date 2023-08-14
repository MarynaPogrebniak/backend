package de.ait.todolist.controllers;

import de.ait.todolist.controllers.api.UsersApi;
import de.ait.todolist.dto.*;
import de.ait.todolist.dto.pages.TasksDto;
import de.ait.todolist.dto.pages.UsersDto;
import de.ait.todolist.services.TasksService;
import de.ait.todolist.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UsersController implements UsersApi {

    private final UsersService usersService;
    private final TasksService tasksService;


    @Override
    public ResponseEntity<UserDto> addUser(NewUserDto newUser) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usersService.addUser(newUser));
    }

    @Override
    public ResponseEntity<UsersDto> getAllUsers(UsersRequest request) {
        return ResponseEntity
                .ok(usersService.getAllUsers(request));
    }

    @Override
    public ResponseEntity<TasksDto> getTasksOfUser(Long userId) {
        return ResponseEntity
                .ok(usersService.getTasksOfUser(userId));
    }

    @Override
    public ResponseEntity<UserDto> updateUser(Long userId, UpdateUserDto updateUser) {
        return ResponseEntity
                .ok(usersService.updateUser(userId, updateUser));
    }

    @Override
    public ResponseEntity<UserDto> getUser(Long userId) {
        return ResponseEntity
                .ok(usersService.getUser(userId));
    }

    @Override
    public ResponseEntity<TaskDto> addTask(NewTaskDto newTask, Long userId) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tasksService.addTask(newTask, userId));
    }

    @Override
    public ResponseEntity<TasksDto> getPublishedTasksOfUser(Integer pageNumber, Long userId) {
        return ResponseEntity.ok(usersService.getPublishedTasksOfUser(pageNumber, userId));
    }
}

