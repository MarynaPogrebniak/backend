package de.ait.timepad.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Данные для обновления")
public class UpdateEventDto {

    @Schema(description = "Название события", example = "Concert")
    private String newName;

    @Schema(description = "Место проведения", example = "Stuttgart")
    private String newLocation;

    @Schema(description = "Статус события - CREATED - создано, " +
            "IN_PROGRESS - проходит сейчас, COMPLETED - завершено, DELETED - удалено", example = "CREATED")
    private String newState;

}
