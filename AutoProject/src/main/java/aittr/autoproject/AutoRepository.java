package aittr.autoproject;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AutoRepository {

    private List<Auto> list = new ArrayList<>(
            List.of(new Auto("Audi", "KN LK 221")
                    , new Auto("Volkswagen", "B NK 113")
                    , new Auto("Mercedes", "A 160")
                    , new Auto("Ford", "F BN 876")
                    , new Auto("Honda", "H FD 277")
            ));

    public List<Auto> findAll() {
        return list;
    }

    public Optional<Auto> findById(Long id) {
        return list.stream()
                .filter(auto -> auto.getId().equals(id))
                .findFirst();
    }
}
