package de.ait.todolist.dto;

import de.ait.todolist.validation.constraints.BeforeCurrentDate;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@Schema(description = "Добавляемая задача")
@BeforeCurrentDate
public class NewTaskDto {

    @NotNull
    @NotBlank
    @Schema(description = "Название задачи", example = "Название задачи...")
    private String title;

    @NotNull
    @NotBlank
    @Schema(description = "Текст задачи", example = "Текст задачи...")
    private String description;

    @NotBlank
    @NotNull
    @Schema(description = "Дата начала задачи в формате YYYY-MM-DD", example = "2022-02-02")
    private String startDate;

    @NotBlank
    @NotNull
    @Schema(description = "Дата окончания задачи в формате YYYY-MM-DD", example = "2022-09-02")
    private String finishDate;
}