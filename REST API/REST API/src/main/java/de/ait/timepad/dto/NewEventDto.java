package de.ait.timepad.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Schema(description = "Данные для добавления события")
public class NewEventDto {

    @Schema(description = "Название события", example = "Concert")
    @NotNull
    @NotBlank
    private String name;

    @Schema(description = "Место проведения", example = "Berlin")
    @NotNull
    @NotBlank
    private String location;

    @Schema(description = "Стоимость билета", example = "100")
    private Integer price;
}
