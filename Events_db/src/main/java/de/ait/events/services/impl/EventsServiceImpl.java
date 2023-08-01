package de.ait.events.services.impl;

import de.ait.events.dto.EventDto;
import de.ait.events.repositories.EventsRepository;
import de.ait.events.services.EventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static de.ait.events.dto.EventDto.from;

@Service
@RequiredArgsConstructor
public class EventsServiceImpl implements EventsService {

    private final EventsRepository eventsRepository;
    @Override
    public List<EventDto> getAllEvents() {
        return from(eventsRepository.findAll());
    }
}
