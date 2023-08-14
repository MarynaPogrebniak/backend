package de.ait.todolist.services;

import de.ait.todolist.dto.*;
import de.ait.todolist.dto.pages.TasksDto;
import de.ait.todolist.dto.pages.UsersDto;

public interface UsersService {
    UserDto addUser(NewUserDto newUser);

    UsersDto getAllUsers(UsersRequest request);

  //  UserDto deleteUser(Long userId);

    UserDto getUser(Long userId);

    TasksDto getTasksOfUser(Long userId);

    UserDto updateUser(Long userId, UpdateUserDto updateUser);

    TasksDto getPublishedTasksOfUser(Integer pageNumber, Long userId);
}
