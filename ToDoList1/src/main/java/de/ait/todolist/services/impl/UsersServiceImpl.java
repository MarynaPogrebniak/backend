package de.ait.todolist.services.impl;

import de.ait.todolist.dto.*;
import de.ait.todolist.dto.pages.TasksDto;
import de.ait.todolist.dto.pages.UsersDto;
import de.ait.todolist.exceptions.ForbiddenFieldException;
import de.ait.todolist.exceptions.ForbiddenUpdateUserOperationException;
import de.ait.todolist.exceptions.NotFoundException;
import de.ait.todolist.models.Task;
import de.ait.todolist.models.User;
import de.ait.todolist.repositories.TasksRepository;
import de.ait.todolist.repositories.UsersRepository;
import de.ait.todolist.services.UsersService;
import de.ait.todolist.utils.PageRequestUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static de.ait.todolist.dto.TaskDto.from;
import static de.ait.todolist.dto.UserDto.from;
import static de.ait.todolist.dto.UserDto.fromWithTasks;


@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final TasksRepository tasksRepository;

    private final PageRequestUtil pageRequestUtil;

    private final PasswordEncoder passwordEncoder;

    @Value("${users.sort.fields}")
    private List<String> sortFields;

    @Value("${users.filter.fields}")
    private List<String> filterFields;



    @Override
    public UserDto addUser(NewUserDto newUser) {
        User user = User.builder()
                .email(newUser.getEmail())
                .hashPassword(passwordEncoder.encode(newUser.getPassword()))
                .role(User.Role.USER)
                .state(User.State.NOT_CONFIRMED)
                .build();

        usersRepository.save(user);

        return from(user);
    }

    /**
     * Метод для получения списка всех пользователей с пагинацией и сортировкой
     * @param pageNumber номер страницы
     * @param orderByField поле, по которому нужно отсортировать
     * @param desc сортировка в обратном порядке
     * @param filterBy по какому полю нужно сделать фильтрацию
     * @param filterValue значение поля, по которому нужно сделать фильтрацию
     * @param tasksState если задано значение published, то кладем опубликованные статьи
     * @return список пользователей со статьями или без
     */

    @Override
    public UsersDto getAllUsers(UsersRequest request) {

        // получаем запрос на страницу с пользователями с помощью класса-утилиты
        PageRequest pageRequest = pageRequestUtil.getPageRequest(request.getPage(), request.getOrderBy(), request.getDesc(), sortFields);
        // получаем страницу с пользователями на основе запроса на страницу
        Page<User> page = getUsersPage(request.getFilterBy(), request.getFilterValue(), request.getTasks(), pageRequest);
        // формируем результат, который превратиться в JSON
        UsersDto result = UsersDto.builder()
                .count(page.getTotalElements()) // берем количество пользователей в базе
                .pagesCount(page.getTotalPages()) // берем общее количество страниц
                .build();

        if (isRequestForPublishedTasks(request.getTasks())) { // если попросили опубликованные статьи
            result.setUsers(fromWithTasks(page.getContent())); // берем пользователей с их статьями
        } else { // если не просили статьи
            result.setUsers(from(page.getContent())); // не берем статьи
        }

        return result;
    }

    /**
     * Проверяем, запросили ли опубликованные задачи
     * @param tasksState published - значит просим опубликованные
     * @return <code>true</code> если запросили опубликованные задачи
     */

    private static boolean isRequestForPublishedTasks(String tasksState) {
        return tasksState != null && tasksState.equals("published");
    }

    private Page<User> getUsersPage(String filterBy, String filterValue, String tasks, PageRequest pageRequest) {
        Page<User> page = Page.empty();
        if (isRequestForPublishedTasks(tasks)) { // если у вас попросили опубликованные статьи
            page = usersRepository.findAll(pageRequest); // пока просто возвращаем всех пользователей
        }
        else if (filterBy == null || filterBy.isEmpty()) { // если не была задана фильтрация
            page = usersRepository.findAll(pageRequest); // просто возвращаем данные
        } else { // если была задана фильтрация
            pageRequestUtil.checkField(filterFields, filterBy); // проверяем поле - есть ли оно в списке разрешенных для фильтрации полей
            if (filterBy.equals("role")) {
                User.Role role = User.Role.valueOf(filterValue);
                page = usersRepository.findAllByRole(role, pageRequest);
            } else if (filterBy.equals("state")) {
                User.State state = User.State.valueOf(filterValue);
                page = usersRepository.findAllByState(state, pageRequest);
            }
        }
        return page;
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
                .count((long) user.getTasks().size())
                .build();
    }

    private User getUserOrThrow(Long userId) {
        return usersRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User", userId));
    }

    @Override
    public UserDto updateUser(Long userId, UpdateUserDto updateUser) {

        User user = getUserOrThrow(userId);

        if (updateUser.getNewRole().equals("ADMIN")) {
            throw new ForbiddenUpdateUserOperationException("role", "ADMIN");
        }

        if (updateUser.getNewState().equals("BANNED")) {
            throw new ForbiddenUpdateUserOperationException("state", "BANNED");
        }

        user.setState(User.State.valueOf(updateUser.getNewState()));
        user.setRole(User.Role.valueOf(updateUser.getNewRole()));

        usersRepository.save(user);

        return from(user);
    }

    @Override
    public TasksDto getPublishedTasksOfUser(Integer pageNumber, Long userId) {
        User user = getUserOrThrow(userId);

        PageRequest pageRequest = pageRequestUtil.getDefaultPageRequest(pageNumber);
        Page<Task> page = tasksRepository.findAllByExecutorAndState(pageRequest, user, Task.State.PUBLISHED);

        return TasksDto.builder()
                .tasks(from(page.getContent()))
                .count((long) page.getContent().size())
                .pagesCount(page.getTotalPages())
                .build();
    }
}
