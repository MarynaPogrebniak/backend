package de.ait.todolist.dto;

import de.ait.todolist.validation.constraints.NotWeakPassword;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@Builder
@Schema(description = "Данные для добавления пользователя")
public class NewUserDto {

    @Schema(description = "Email пользователя", example = "test@gmail.com")
    @Email
    @NotNull
    @NotBlank
    private String email;

    @Schema(description = "Пароль пользователя", example = "qwerty007")
    @NotBlank
    @Size(min = 7, max = 100)
    @NotWeakPassword
    private String password;
}