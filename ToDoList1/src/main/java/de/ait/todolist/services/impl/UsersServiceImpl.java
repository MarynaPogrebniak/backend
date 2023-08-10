package de.ait.todolist.services.impl;

import de.ait.todolist.dto.*;
import de.ait.todolist.exceptions.ForbiddenFieldException;
import de.ait.todolist.exceptions.ForbiddenUpdateUserOperationException;
import de.ait.todolist.exceptions.NotFoundException;
import de.ait.todolist.models.User;
import de.ait.todolist.repositories.UsersRepository;
import de.ait.todolist.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static de.ait.todolist.dto.TaskDto.from;
import static de.ait.todolist.dto.UserDto.from;


@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Value("${users.sort.fields}")
    private List<String> sortFields;

    @Value("${users.filter.fields}")
    private List<String> filterFields;

    @Value("${users.page.size}")
    private Integer pageSize;

    @Override
    public UserDto addUser(NewUserDto newUser) {
        User user = User.builder()
                .email(newUser.getEmail())
                .password(newUser.getPassword())
                .role(User.Role.USER)
                .state(User.State.NOT_CONFIRMED)
                .build();

        usersRepository.save(user);

        return from(user);
    }

    @Override
    public UsersDto getAllUsers(Integer pageNumber,
                                String orderByField,
                                Boolean desc,
                                String filterBy,
                                String filterValue) {
        // создаем запрос на страницу
        PageRequest pageRequest = getPageRequest(pageNumber, orderByField, desc);

        Page<User> page = getUsersPage(filterBy, filterValue, pageRequest);

        return UsersDto.builder()
                .users(from(page.getContent())) // берем самих пользователей из страницы
                .count(page.getTotalElements()) // берем количество пользователей в базе
                .pagesCount(page.getTotalPages()) // берем общее количество страниц
                .build();
    }

    private Page<User> getUsersPage(String filterBy, String filterValue, PageRequest pageRequest) {
        Page<User> page = Page.empty();
        if (filterBy == null || filterBy.isEmpty()) { // если не была задана фильтрация
            page = usersRepository.findAll(pageRequest); // просто возвращаем данные
        } else { // если была задана фильтрация
            checkField(filterFields, filterBy); // проверяем поле - есть ли оно в списке разрешенных для фильтрации полей
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
}
