package de.aittr.products.models;

import lombok.*;

@ToString
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private String name;
    private int count;
    private boolean expired;
}
