package de.ait.timepad.dto;

import lombok.Data;

@Data
public class NewEventDto {

    private String name;
    private String location;
    private Integer price;
}
