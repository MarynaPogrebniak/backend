package de.ait.todolist.services;

import de.ait.todolist.dto.NewTaskDto;
import de.ait.todolist.dto.TaskDto;
import de.ait.todolist.dto.pages.TasksDto;

public interface TasksService {
    TaskDto addTask(NewTaskDto newTask, Long UserId);

    TasksDto getAllTasks(Integer pageNumber,
                         String orderByField,
                         Boolean desc,
                         String filterBy,
                         String filterValue);
}
