package de.ait.todolist.dto;

import de.ait.todolist.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Пользователь")
public class UserDto {

    @Schema(description = "Идентификатор пользователя", example = "1")
    private Long id;

    @Schema(description = "Email пользователя", example = "test@gmail.com" )
    private String email;

    @Schema(description = "Роль пользователя - ADMIN - администратор, USER - пользователь, MANAGER - менеджер", example = "ADMIN")
    private String role;

    @Schema(description = "Статус пользователя - NOT_CONFIRMED - не подтвержден, " +
            "CONFIRMED - подтвержден, BANNED - забанен, DELETED - удален", example = "CONFIRMED")
    private String state;

    @Schema(description = "Список опубликованных задач пользователя")
    private List<TaskDto> publishedTasks;

    @Schema(description = "Список всех задач пользователя")
    private List<TaskDto> tasks;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .state(user.getState().name())
                .role(user.getRole().name())
                .build();
    }

    public static UserDto fromWithTasks(User user) {
        UserDto result = from(user);

        if (user.getTasks() != null) {
            result.setTasks(TaskDto.from(user.getTasks()));
        }

        if (user.getPublishedTasks() != null) {
            result.setPublishedTasks(TaskDto.from(user.getPublishedTasks()));
        }

        return result;
    }

    public static List<UserDto> from(List<User> users) {
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }

    public static List<UserDto> fromWithTasks(List<User> users) {
        return users.stream()
                .map(UserDto::fromWithTasks)
                .collect(Collectors.toList());
    }
}