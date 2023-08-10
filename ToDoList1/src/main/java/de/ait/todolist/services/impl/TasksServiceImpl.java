package de.ait.todolist.services.impl;


import de.ait.todolist.dto.NewTaskDto;
import de.ait.todolist.dto.TaskDto;
import de.ait.todolist.dto.TasksDto;
import de.ait.todolist.exceptions.ForbiddenFieldException;
import de.ait.todolist.exceptions.IncorrectUserIdException;
import de.ait.todolist.exceptions.NotFoundException;
import de.ait.todolist.models.Task;
import de.ait.todolist.models.User;
import de.ait.todolist.repositories.TasksRepository;
import de.ait.todolist.repositories.UsersRepository;
import de.ait.todolist.services.TasksService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static de.ait.todolist.dto.TaskDto.from;

@RequiredArgsConstructor
@Service
public class TasksServiceImpl implements TasksService {

    private final UsersRepository usersRepository;

    private final TasksRepository tasksRepository;

    @Value("${tasks.sort.fields}")
    private List<String> sortFields;

    @Value("${tasks.filter.fields}")
    private List<String> filterFields;

    @Value("${users.page.size}")
    private Integer pageSize;

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
                () -> new IncorrectUserIdException(userId));
    }

    @Override
    public TasksDto getAllTasks(Integer pageNumber,
                                String orderByField,
                                Boolean desc,
                                String filterBy,
                                String filterValue) {
        // создаем запрос на страницу
        PageRequest pageRequest = getPageRequest(pageNumber, orderByField, desc);

        Page<Task> page = getTasksPage(filterBy, filterValue, pageRequest);

        return TasksDto.builder()
                .tasks(from(page.getContent())) // берем самих пользователей из страницы
                .count(page.getTotalElements()) // берем количество пользователей в базе
                .pagesCount(page.getTotalPages()) // берем общее количество страниц
                .build();
    }

    private Page<Task> getTasksPage(String filterBy, String filterValue, PageRequest pageRequest) {
        Page<Task> page = Page.empty();
        if (filterBy == null || filterBy.isEmpty()) { // если не была задана фильтрация
            page = tasksRepository.findAll(pageRequest); // просто возвращаем данные
        } else { // если была задана фильтрация
            checkField(filterFields, filterBy); // проверяем поле - есть ли оно в списке разрешенных для фильтрации полей
            if (filterBy.equals("startDate")) {
                LocalDate startDate = LocalDate.parse(filterValue); // Парсим строку в LocalDate
                page = tasksRepository.findAllByStartDate(startDate, pageRequest);
            } else if (filterBy.equals("finishDate")) {
                LocalDate finishDate = LocalDate.parse(filterValue); // Парсим строку в LocalDate
                page = tasksRepository.findAllByFinishDate(finishDate, pageRequest);
            }
        }
        return page;
    }

    private PageRequest getPageRequest(Integer pageNumber, String orderByField, Boolean desc) {

        if (orderByField != null && !orderByField.isEmpty()) { // проверяем, задал ли клиент поле для сортировки?

            checkField(sortFields, orderByField); // проверяем, доступно ли нам поле для сортировки

            Sort.Direction direction = Sort.Direction.ASC; // предполагаем, что сортировка будет в прямом порядке

            if (desc != null && desc) { // если клиент задал сортировку в обратном порядке
                direction = Sort.Direction.DESC; // задаем обратный порядок сортировки
            }

            Sort sort = Sort.by(direction, orderByField); // создаем объект для сортировки направление + поле

            return PageRequest.of(pageNumber, pageSize, sort); // создаем запрос на получение страницы пользователей с сортировкой
        } else {
            return PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "id")); // если пользователь не задал сортировку - сортируем по id
        }
    }

    private void checkField(List<String> allowedFields, String field) {
        if (!allowedFields.contains(field)) {
            throw new ForbiddenFieldException(field);
        }
    }

}
