package de.ait.todolist.services.impl;

import de.ait.todolist.dto.*;
import de.ait.todolist.exceptions.ForbiddenOperationException;
import de.ait.todolist.exceptions.NotFoundException;
import de.ait.todolist.models.User;
import de.ait.todolist.repositories.UsersRepository;
import de.ait.todolist.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static de.ait.todolist.dto.TaskDto.from;
import static de.ait.todolist.dto.UserDto.from;


@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Override
    public UserDto addUser(NewUserDto newUser) {
        User user = User.builder()
                .email(newUser.getEmail())
                .password(newUser.getPassword())
                .role(User.Role.USER)
                .build();

        usersRepository.save(user);

        return from(user);
    }

    @Override
    public UsersDto getAllUsers() {
        List<User> users = usersRepository.findAll();
        return UsersDto.builder()
                .users(from(users))
                .count(users.size())
                .build();
    }

    @Override
    public UserDto deleteUser(Long userId) {
        User user = getUserOrThrow(userId);

        usersRepository.delete(user);

        return from(user);
    }

    @Override
    public UserDto updateUser(Long userId, UpdateUserDto updateUser) {

        User user = getUserOrThrow(userId);

        if (updateUser.getNewRole().equals("ADMIN")) {
            throw new ForbiddenOperationException("Cannot make an administrator");
        }

        user.setRole(User.Role.valueOf(updateUser.getNewRole()));

        usersRepository.save(user);

        return from(user);
    }

    @Override
    public UserDto getUser(Long userId) {
        return from(getUserOrThrow(userId));
    }

    @Override
    public TasksDto getTasksOfUser(Long userId) {
        User user = getUserOrThrow(userId);

        return TasksDto.builder()
                .tasks(from(user.getTasks()))
                .count(user.getTasks().size())
                .build();
    }

    private User getUserOrThrow(Long userId) {
        return usersRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User with id <" + userId + "> not found"));
    }
}
