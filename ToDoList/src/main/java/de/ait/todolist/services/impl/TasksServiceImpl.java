package de.ait.todolist.services.impl;


import de.ait.todolist.dto.NewTaskDto;
import de.ait.todolist.dto.TaskDto;
import de.ait.todolist.exceptions.NotFoundException;
import de.ait.todolist.models.Task;
import de.ait.todolist.models.User;
import de.ait.todolist.repositories.TasksRepository;
import de.ait.todolist.repositories.UsersRepository;
import de.ait.todolist.services.TasksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static de.ait.todolist.dto.TaskDto.from;



@RequiredArgsConstructor
@Service
public class TasksServiceImpl implements TasksService {

    private final UsersRepository usersRepository;

    private final TasksRepository tasksRepository;

    @Override
    public TaskDto addTask(NewTaskDto newTask, Long userId) {
        User user = getUserOrThrow(userId);

        Task task = Task.builder()
                .title(newTask.getTitle())
                .description(newTask.getDescription())
                .executor(user)
                .startDate(LocalDate.parse(newTask.getStartDate()))
                .finishDate(LocalDate.parse(newTask.getFinishDate()))
                .build();

        tasksRepository.save(task);

        return from(task);
    }

    private User getUserOrThrow(Long userId) {
        return usersRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User with id <" + userId + "> not found"));
    }
}
