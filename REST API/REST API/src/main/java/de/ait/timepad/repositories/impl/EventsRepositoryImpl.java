package de.ait.timepad.repositories.impl;

import de.ait.timepad.models.Event;
import de.ait.timepad.models.User;
import de.ait.timepad.repositories.EventsRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EventsRepositoryImpl implements EventsRepository {

    private static List<Event> events = new ArrayList<>();

    @Override
    public void save(Event event) {
        if (event.getId() == null) {
            event.setId((long) events.size() +1);
            events.add(event);
        } else {
            // так как это список, не обновляем. В файле и БД надо
        }

    }

    @Override
    public List<Event> findAll() {
        return new ArrayList<>(events);
    }

    @Override
    public Optional<Event> findById(Long id) {
        for (Event event : events) {
            if (event.getId().equals(id)) {
                return Optional.of(event); // вместо return event
            }
        }

        return Optional.empty(); // вместо return null
    }

    @Override
    public void delete(Event event) {
        events.remove(event);
    }

    @Override
    public void clear() {
        events.clear();
    }
}
