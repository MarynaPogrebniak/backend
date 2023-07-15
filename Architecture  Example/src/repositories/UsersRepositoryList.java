package repositories;

import models.User;

import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryList {

    private List<User> users = new ArrayList<>();

    public void save (User user) {
        this.users.add(user);
    }

    public List<User> findAll() {
        return users;
    }
}
