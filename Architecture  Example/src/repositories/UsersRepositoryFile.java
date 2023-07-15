package repositories;

import models.User;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class UsersRepositoryFile {

    private String fileName = "users.txt";
    public void save(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(user.getEmail() + "#" + user.getPassword());
        } catch (IOException e) {
            throw new IllegalArgumentException("Проблемы с файлом");
        }
    }

    public List<User> findAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines()
                    .map(line -> line.split("#")) // разбили строки по #
                    .map(array -> new User(array[0], array[1]))// превратили массив строк в объект
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalArgumentException("Проблемы с файлом");

        }
    }
}
