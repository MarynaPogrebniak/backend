import repositories.impl.UsersRepositoryFileImpl;
import repositories.impl.UsersRepositoryListImpl;
import services.UsersService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // прложение для регистрации пользователя
        Scanner scanner = new Scanner(System.in);

        UsersRepositoryListImpl repositoryList = new UsersRepositoryListImpl();
        UsersRepositoryFileImpl repositoryFile = new UsersRepositoryFileImpl();

        UsersService usersService = new UsersService(repositoryFile);

        while (true) {
            System.out.println("0. Выход");
            System.out.println("1. Зарегистрировать пользователя");
            System.out.println("2. Список всех пользователей");

            int command = scanner.nextInt();
            scanner.nextLine();

            switch (command) {
                case 1 -> {
                    String email = scanner.nextLine();
                    String password = scanner.nextLine();
                    usersService.signUp(email, password);
                }
                case 2 -> System.out.println(usersService.getAllUsers());
                case 0 -> System.exit(0);
            }
        }
    }



}