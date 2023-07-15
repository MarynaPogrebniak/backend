package de.ait.rentacar.models;

import lombok.*;

@ToString
@EqualsAndHashCode // для авто генерации equals и hashcode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private Long id;
    private String model;
    private String color;

}
