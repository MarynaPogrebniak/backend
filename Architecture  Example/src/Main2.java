import models.User;
import repositories.UsersRepositoryFile;

public class Main2 {
    public static void main(String[] args) {
        UsersRepositoryFile usersRepository = new UsersRepositoryFile();
        User user = new User("m@gmail.com", "qwerty");
        usersRepository.save(user);
    }
}
