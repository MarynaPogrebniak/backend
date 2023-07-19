package de.aittr.products.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private String name;
    private int count;
    private boolean expired;
}
