package de.aittr.auto;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AutoRepository {

    private static List<Auto> list = new ArrayList<>(List.of(
            new Auto(1L, "b14233", "Audi"),
            new Auto(2L, "Bj2345", "VW"),
            new Auto(3L, "Bj2555", "Mercedes"),
            new Auto(4L, "Lj2345", "Volvo")
    ));

    public List<Auto> findAll() {
        return list;
    }

    public Auto save(Auto auto) {
        list.add(auto);
        return auto;
    }

    public Optional<Auto> findByID(Long id) {
        return list.stream()
                .filter(auto -> auto.getId().equals(id))
                .findFirst();
    }
}
