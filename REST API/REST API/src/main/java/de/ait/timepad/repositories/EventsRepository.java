package de.ait.timepad.repositories;

import de.ait.timepad.models.Event;

import java.util.List;
import java.util.Optional;

public interface EventsRepository {

    void save (Event event);

    List<Event> findAll();

    Optional<Event> findById (Long id);

    void delete(Event event);

    // TODO: убрать метод, как только подключим базы данных
    void clear();
}
