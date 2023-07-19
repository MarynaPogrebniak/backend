package de.aittr.products;

import de.aittr.products.config.ApplicationConfig;
import de.aittr.products.services.ProductsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class ProductsApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        ProductsService productsService = context.getBean(ProductsService.class);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Добавить продукт");
            System.out.println("2. Показать все добавленные продукты");
            System.out.println("3. Выход");

            int command = scanner.nextInt();
            scanner.nextLine();

            switch (command) {
                case 1 -> {
                    String name = scanner.nextLine();
                    int count = Integer.parseInt(scanner.nextLine());
                    boolean expired = scanner.nextBoolean();

                    productsService.addProduct(name, count, expired);
                }
                case 2 -> System.out.println(productsService.getAllProducts());
                case 3 -> System.exit(0);
            }
        }
    }
}
