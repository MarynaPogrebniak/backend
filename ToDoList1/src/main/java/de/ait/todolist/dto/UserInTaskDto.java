package de.ait.todolist.dto;

import de.ait.todolist.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Информация о задаче пользователя")
public class UserInTaskDto {

    @Schema(description = "Идентификатор пользователя", example = "1")
    private Long id;

    @Schema(description = "Email пользователя", example = "user@mail.com")
    private String email;

    public static UserInTaskDto from(User user) {
        return UserInTaskDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }
}