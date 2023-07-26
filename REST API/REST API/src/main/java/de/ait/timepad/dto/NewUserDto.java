package de.ait.timepad.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Данные для добавления пользователя")
public class NewUserDto {

    @Schema(description = "Email пользователя", example = "simple@mail.com")
    private String email;

    @Schema(description = "Пароль пользователя", example = "qwerty007")
    private String password;
}