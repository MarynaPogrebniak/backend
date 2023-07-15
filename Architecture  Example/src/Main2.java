import models.User;
import repositories.impl.UsersRepositoryFileImpl;

public class Main2 {
    public static void main(String[] args) {
        UsersRepositoryFileImpl usersRepository = new UsersRepositoryFileImpl();
        User user = new User("m@gmail.com", "qwerty");
        usersRepository.save(user);
    }
}
