package com.api.rest.repository;

import com.api.rest.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByPriceLessThanEqual(Double price);

    Optional<Product> findByTitle(String productoTest);

}