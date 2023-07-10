package aittr.mvc;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private List<User> list = new ArrayList<>(
            List.of(new User("Sam", "Berlin")
                , new User("Jack", "Hamburg")
                , new User("Katrin", "Stuttgart")
                , new User("John", "Rome")
                , new User("Mary", "Paris")
            ));

    public List<User> findAll() {
        return list;
    }

    public Optional<User> findById(Long id) {
       return list.stream()
               .filter(u -> u.getId().equals(id))
               .findFirst();
    }
}
