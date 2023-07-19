package de.aittr.products.services;

import de.aittr.products.models.Product;

import java.util.List;

public interface ProductsService {

    void addProduct(String name, int count, boolean expired);
    List<Product> getAllProducts();
}
