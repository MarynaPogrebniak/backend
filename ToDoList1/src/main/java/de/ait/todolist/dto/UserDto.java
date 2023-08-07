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


    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }

    public static List<UserDto> from(List<User> users) {
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }
}