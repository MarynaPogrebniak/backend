package de.ait.timepad.dto;

import de.ait.timepad.models.Event;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Событие")
public class EventDto {

    @Schema(description = "Идентификатор события", example = "1")
    private Long id;

    @Schema(description = "Название события", example = "Concert")
    private String name;

    @Schema(description = "Место проведения", example = "Stuttgart")
    private String location;

    @Schema(description = "Статус события - CREATED - создано, " +
            "IN_PROGRESS - проходит сейчас, COMPLETED - завершено, DELETED - удалено", example = "CREATED")
    private String state;


    public static EventDto from(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .location(event.getLocation())
                .state(event.getState().name())
                .build();
    }

    public static java.util.List<EventDto> from(List<Event> events) {
        return events.stream()
                .map(EventDto::from)
                .collect(Collectors.toList());
    }
}
