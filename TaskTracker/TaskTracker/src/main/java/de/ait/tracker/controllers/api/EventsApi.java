package de.ait.tracker.controllers.api;

import de.ait.tracker.dto.EventDto;
import de.ait.tracker.dto.NewEventDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/events")
public interface EventsApi {

    @PostMapping
    EventDto addEvent(@RequestBody NewEventDto newEvent);
}
