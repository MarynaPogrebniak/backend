package de.ait.repositories;


import de.ait.models.User;

import java.util.List;

public interface UsersRepository {
    void save(User user);

    List<User> findAll();
}
