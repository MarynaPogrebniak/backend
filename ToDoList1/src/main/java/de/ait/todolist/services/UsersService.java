package de.ait.todolist.services;

import de.ait.todolist.dto.*;

public interface UsersService {
    UserDto addUser(NewUserDto newUser);

    UsersDto getAllUsers(Integer pageNumber, String orderByField, Boolean desc, String filterBy, String filterValue);

  //  UserDto deleteUser(Long userId);

    UserDto getUser(Long userId);

    TasksDto getTasksOfUser(Long userId);

    UserDto updateUser(Long userId, UpdateUserDto updateUser);
}
