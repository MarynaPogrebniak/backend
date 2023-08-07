package de.ait.todolist.dto;

import de.ait.todolist.validation.constraints.BeforeCurrentDate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Добавляемая задача")
public class NewTaskDto {

    @NotNull
    @NotBlank
    @Schema(description = "Название задачи", example = "Название задачи...")
    private String title;

    @NotNull
    @NotBlank
    @Schema(description = "Текст задачи", example = "Текст задачи...")
    private String description;

    @BeforeCurrentDate
    @NotNull
    @NotBlank
    @Schema(description = "Дата начала задачи в формате YYYY-MM-DD", example = "2022-02-02")
    private String startDate;

    @NotBlank
    @BeforeCurrentDate
    @Schema(description = "Дата окончания задачи в формате YYYY-MM-DD", example = "2022-09-02")
    private String finishDate;
}