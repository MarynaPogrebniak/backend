package de.ait.todolist.services;

import de.ait.todolist.dto.*;

public interface UsersService {
    UserDto addUser(NewUserDto newUser);

    UsersDto getAllUsers();

  //  UserDto deleteUser(Long userId);

  //  UserDto updateUser(Long userId, UpdateUserDto updateUser);

    UserDto getUser(Long userId);

    TasksDto getTasksOfUser(Long userId);
}
