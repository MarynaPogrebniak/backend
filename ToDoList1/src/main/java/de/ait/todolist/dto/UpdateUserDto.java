package de.ait.todolist.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "Данные для обновления")
@Builder
public class UpdateUserDto {

    @Schema(description = "Роль пользователя - USER - пользователь", example = "USER")
    private String newRole;

    @Schema(description = "Статус пользователя - NOT_CONFIRMED - не подтвержден, " +
            "CONFIRMED - подтвержден, BANNED - забанен, DELETED - удален", example = "CONFIRMED")
    private String newState;

}