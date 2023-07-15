package repositories;

import models.User;

import java.util.List;

public interface UsersRepository {

    void save (User user);

    List<User> findAll();

}
