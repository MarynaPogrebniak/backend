package de.ait.timepad.services.impl;

import de.ait.timepad.dto.EventDto;
import de.ait.timepad.dto.EventsDto;
import de.ait.timepad.dto.NewEventDto;
import de.ait.timepad.dto.UpdateEventDto;
import de.ait.timepad.exceptions.NotFoundException;
import de.ait.timepad.models.Event;
import de.ait.timepad.repositories.EventsRepository;
import de.ait.timepad.services.EventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static de.ait.timepad.dto.EventDto.from;

@RequiredArgsConstructor
@Service
public class EventsServiceImpl implements EventsService {

    private final EventsRepository eventsRepository;

    @Override
    public EventDto addEvent(NewEventDto newEvent) {

        Event event = Event.builder()
                .name(newEvent.getName())
                .location(newEvent.getLocation())
                .price(newEvent.getPrice())
                .state(Event.State.CREATED)
                .build();


        eventsRepository.save(event);

        return from(event); // за счет static import
    }

    @Override
    public EventsDto getAllEvents() {
        // List<Event> events = eventsRepository.findAll();

        //  List<EventDto> dtos = new ArrayList<>();

        //   for (Event event: events) {
        //      EventDto eventDto = from(event);
        //       dtos.add(eventDto);
        //   }

        List<Event> events = eventsRepository.findAll();

        return EventsDto.builder()
                .events(from(events))
                .count(events.size())
                .build();
    }

    @Override
    public EventDto deleteEvent(Long eventId) {
       // Optional<Event> event = eventsRepository.findById(eventId);

      //  if (event.isEmpty()) {
      //      throw new NotFoundException("Event with id <" + eventId + "> not found");
      //  }

      //  eventsRepository.delete(event.get());

        Event event = getEventOrThrow(eventId);

        eventsRepository.delete(event);

        return from(event);
    }

    @Override
    public EventDto updateEvent(Long eventId, UpdateEventDto updateEvent) {

        Event event = getEventOrThrow(eventId);

        event.setState(Event.State.valueOf(updateEvent.getNewName()));
        event.setState(Event.State.valueOf(updateEvent.getNewLocation()));
        event.setState(Event.State.valueOf(updateEvent.getNewState()));

        eventsRepository.save(event);
        return from(event);
    }

    @Override
    public EventDto getEvent(Long eventId) {
        return from(getEventOrThrow(eventId));
    }

    private Event getEventOrThrow(Long eventId) {
        return eventsRepository.findById(eventId).orElseThrow(() ->
                new NotFoundException("Event with id <" + eventId + "> not found"));
    }
}
