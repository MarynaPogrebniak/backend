package de.aittr.products.services.impl;

import de.aittr.products.models.Product;
import de.aittr.products.repositories.ProductsRepository;
import de.aittr.products.services.ProductsService;
import de.aittr.products.validators.NameValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductsServiceImpl implements ProductsService {

   private final ProductsRepository productsRepository;
   private final NameValidator nameValidator;


    @Override
    public void addProduct(String name, int count, boolean expired) {
        nameValidator.validate(name);
        Product product = new Product(name, count, expired);
        productsRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productsRepository.findAll();
    }
}
