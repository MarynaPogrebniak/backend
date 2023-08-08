package de.ait.todolist.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Информация об ошибке")
public class ErrorDto {

    @Schema(description = "Сообщение об ошибке", example = "Пользователь с указанным ID не найден")
    private String message;
}
