package de.aittr.products.controllers;

import de.aittr.products.models.Product;
import de.aittr.products.services.ProductsService;
import de.aittr.products.services.impl.ProductsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController (ProductsService service) {
        this.productsService = service;
    }

    @GetMapping("/products")
    @ResponseBody
    public List<Product> getAllProducts() {
        return productsService.getAllProducts();
    }
}
