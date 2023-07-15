package de.aittr.products.repositories.impl;

import de.aittr.products.models.Product;
import de.aittr.products.repositories.ProductsRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ProductsRepositoryListImpl implements ProductsRepository {

    private List<Product> products = new ArrayList<>();

    public ProductsRepositoryListImpl() {
        Product product1 = new Product("Tea", 10, true);
        Product product2 = new Product("Coffee", 15, false);
        Product product3 = new Product("Butter", 20, true);
        Product product4 = new Product("Lemons", 12, false);

        this.products = Arrays.asList(product1, product2, product3, product4);
    }

    @Override
    public List<Product> findAll() {
        return products;
    }
}
