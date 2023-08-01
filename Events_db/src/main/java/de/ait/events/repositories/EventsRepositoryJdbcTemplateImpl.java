package de.ait.events.repositories;

import de.ait.events.models.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class EventsRepositoryJdbcTemplateImpl implements EventsRepository {

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from events";
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Event> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, (row, rowNumber) -> Event
                .builder()
                .id(row.getLong("id"))
                .description(row.getString("description"))
                .title(row.getString("title"))
                .build());
    }
}
