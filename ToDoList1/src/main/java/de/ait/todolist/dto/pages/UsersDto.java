package de.ait.todolist.dto.pages;

import de.ait.todolist.dto.UserDto;
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
@Schema(description = "Список пользователей")
public class UsersDto {
    @Schema(description = "Пользователи системы")
    private List<UserDto> users;

    @Schema(description = "Общее количество пользователей", example = "1")
    private Long count;

    @Schema(description = "Общее количество страниц", example = "3")
    private Integer pagesCount;
}