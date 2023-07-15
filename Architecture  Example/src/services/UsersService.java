package services;

import models.User;
import repositories.UsersRepositoryFile;
import repositories.UsersRepositoryList;

import java.util.ArrayList;
import java.util.List;

public class UsersService {

    private final UsersRepositoryFile usersRepository;

    public UsersService () {
        this.usersRepository = new UsersRepositoryFile();
    }
    public void signUp(String email, String password) {

        if (!email.contains("@")) {
            throw new IllegalArgumentException("Плохой email");
        }

        if (password.length() < 5) {
            throw new IllegalArgumentException("Плохой пароль");
        }

        User user = new User(email, password);
        usersRepository.save(user);
    }

    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }
}
