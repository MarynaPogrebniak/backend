package de.ait.todolist.dto;

import de.ait.todolist.models.Task;
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
@Schema(description = "Задача пользователя")
public class TaskDto {

    @Schema(description = "Идентификатор статьи", example = "1")
    private Long id;

    @Schema(description = "Описание задачи", example = "Задача пользователя...")
    private String description;

    @Schema(description = "Название задачи", example = "Название задачи пользователя...")
    private String title;

    @Schema(description = "Дата начала задачи в формате YYYY-MM-DD", example = "2022-02-02")
    private String startDate;

    @Schema(description = "Дата окончания задачи в формате YYYY-MM-DD", example = "2022-09-02")
    private String finishDate;

    @Schema(description = "Пользователь, чья задача")
    private UserInTaskDto executor;

    public static TaskDto from(Task task) {
        TaskDto result = TaskDto.builder()
                .id(task.getId())
                .description(task.getDescription())
                .title(task.getTitle())
                .build();

        if (task.getExecutor() != null) {
            result.setExecutor(UserInTaskDto.from(task.getExecutor()));
        }

        if (task.getStartDate() != null && task.getFinishDate() !=null) {
            result.setStartDate(task.getStartDate().toString());
            result.setFinishDate(task.getFinishDate().toString());
        }

        return result;
    }

    public static List<TaskDto> from(List<Task> tasks) {
        return tasks.stream()
                .map(TaskDto::from)
                .collect(Collectors.toList());
    }
}

