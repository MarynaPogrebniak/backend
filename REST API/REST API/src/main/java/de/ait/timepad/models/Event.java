package de.ait.timepad.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {

    public enum State {

        CREATED,
        IN_PROGRESS,
        COMPLETED,
        DELETED
    }

    private Long id;
    private String name;
    private String location;
    private Integer price;

    private State state;
}
