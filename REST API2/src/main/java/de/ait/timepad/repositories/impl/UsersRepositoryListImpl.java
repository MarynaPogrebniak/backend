package de.ait.timepad.repositories.impl;

import de.ait.timepad.models.User;
import de.ait.timepad.repositories.UsersRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsersRepositoryListImpl implements UsersRepository {

    private static List<User> users;

    @Override
    public void save(User user) {
        user.setId((long) users.size()); // id пользователя - его порядковый номер в списке
        users.add(user);
    }
}
