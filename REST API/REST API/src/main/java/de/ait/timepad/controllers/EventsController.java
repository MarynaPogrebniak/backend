package de.ait.timepad.controllers;

import de.ait.timepad.controllers.api.EventsApi;
import de.ait.timepad.dto.EventDto;
import de.ait.timepad.dto.EventsDto;
import de.ait.timepad.dto.NewEventDto;
import de.ait.timepad.dto.UpdateEventDto;
import de.ait.timepad.models.Event;
import de.ait.timepad.services.EventsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class EventsController implements EventsApi {

    private final EventsService eventsService;

    @Override
    public EventDto addEvent(NewEventDto newEvent) {
        return eventsService.addEvent(newEvent);
    }

    @Override
    public EventsDto getAllEvents() {
        return eventsService.getAllEvents();
    }

    @Override
    public EventDto deleteEvent(Long eventId) {
        return eventsService.deleteEvent(eventId);
    }

    @Override
    public EventDto updateEvent(Long eventId, UpdateEventDto updateEvent) {
        return eventsService.updateEvent(eventId, updateEvent);
    }

    @Override
    public EventDto getEvent(Long eventId) {
        return eventsService.getEvent(eventId);
    }
}
