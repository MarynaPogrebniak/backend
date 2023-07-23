package de.ait.timepad.services;

import de.ait.timepad.dto.EventDto;
import de.ait.timepad.dto.EventsDto;
import de.ait.timepad.dto.NewEventDto;
import de.ait.timepad.models.Event;

public interface EventsService {

    EventDto addEvent(NewEventDto newEvent);

    EventsDto getAllEvents();
}
