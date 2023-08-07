package de.ait.todolist.services;

import de.ait.todolist.dto.NewTaskDto;
import de.ait.todolist.dto.TaskDto;

public interface TasksService {
    TaskDto addTask(NewTaskDto newTask, Long UserId);
}
