package de.ait.timepad.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Данные для добавления события")
public class NewEventDto {

    @Schema(description = "Название события", example = "Concert")
    private String name;

    @Schema(description = "Место проведения", example = "Berlin")
    private String location;

    @Schema(description = "Стоимость билета", example = "100")
    private Integer price;
}
