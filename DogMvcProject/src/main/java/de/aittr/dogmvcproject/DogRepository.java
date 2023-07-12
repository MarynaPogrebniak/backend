package de.aittr.dogmvcproject;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DogRepository {

    private static List<Dog> list = new ArrayList<>(List.of(
            new Dog(1L, "Rex", 8),
            new Dog(2L, "Jack", 2),
            new Dog(3L, "Buddy", 4),
            new Dog(4L, "Mugly", 8)
    ));

    public List<Dog> findAll() {
        return list;
    }
}
