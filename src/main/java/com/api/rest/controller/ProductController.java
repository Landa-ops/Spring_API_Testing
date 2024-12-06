package com.api.rest.controller;

import com.api.rest.entity.Product;
import com.api.rest.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // http://localhost:8080/hola
    @GetMapping("hola")
    public String holaMundo(){
        return "Hola Mundo";
    }

    @GetMapping("api/products")
    public List<Product> findAll(){

        List<Product> products = List.of(
                new Product(1L, "pc", 500d),
                new Product(2L, "laptop", 450d),
                new Product(3L, "botella", 350d)
        );
        //return products;
        return productRepository.findAll();
    }

    @PostMapping("api/products")
    public ResponseEntity <Product> create(@RequestBody Product product) {
        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

}
