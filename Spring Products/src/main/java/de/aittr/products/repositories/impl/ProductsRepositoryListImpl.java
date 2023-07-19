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

    @Override
    public void save(Product product) {
        this.products.add(product);
    }

    @Override
    public List<Product> findAll() {
        return products;
    }
}
