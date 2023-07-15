package services;

import models.User;
import repositories.UsersRepository;
import repositories.impl.UsersRepositoryFileImpl;

import java.util.List;

public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService (UsersRepository usersRepository) {
        this.usersRepository = new UsersRepositoryFileImpl();
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
