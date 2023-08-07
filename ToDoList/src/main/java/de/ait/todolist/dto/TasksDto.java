package de.ait.todolist.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Задачи пользователя")
public class TasksDto {

    @Schema(description = "Список задач")
    private List<TaskDto> tasks;

    @Schema(description = "Количество задач пользователя", example = "3")
    private Integer count;
}