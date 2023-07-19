package de.aittr.products.repositories;

import de.aittr.products.models.Product;

import java.util.List;

public interface ProductsRepository {

    void save(Product product);

    List<Product> findAll();
}
