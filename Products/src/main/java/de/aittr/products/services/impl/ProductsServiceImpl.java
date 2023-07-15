package de.aittr.products.services.impl;

import de.aittr.products.models.Product;
import de.aittr.products.repositories.ProductsRepository;
import de.aittr.products.services.ProductsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {

   private final ProductsRepository productsRepository;
    public ProductsServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }
    @Override
    public List<Product> getAllProducts() {
        return productsRepository.findAll();
    }
}
