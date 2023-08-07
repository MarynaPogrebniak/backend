package de.ait.todolist.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Данные для обновления")
public class UpdateUserDto {

    @Schema(description = "Роль пользователя - USER - пользователь", example = "USER")
    private String newRole;

}