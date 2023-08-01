package de.ait.events.dto;

import de.ait.events.models.Event;
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
public class EventDto {

    private Long id;

    private String description;

    private String title;

    public static EventDto from(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .description(event.getDescription())
                .title(event.getTitle())
                .build();
    }
    public static List<EventDto> from(List<Event> events) {
        return events.stream()
                .map(EventDto::from)
                .collect(Collectors.toList());
    }
}
