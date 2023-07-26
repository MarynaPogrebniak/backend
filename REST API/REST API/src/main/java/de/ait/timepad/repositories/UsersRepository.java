package de.ait.timepad.repositories;

import de.ait.timepad.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository {
    void save(User user);

    List<User> findAll();

    Optional<User> findById(Long id);

    void delete(User user);

    // TODO: убрать метод, как только подключим базы данных
    void clear();
}