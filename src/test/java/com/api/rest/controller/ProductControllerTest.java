package com.api.rest.controller;

import com.api.rest.entity.Product;
import com.api.rest.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;




//Testing de integración
@SpringBootTest
@AutoConfigureMockMvc //herramienta para lanzar peticiones HTTP y probar la aplicación
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;

    //CRUD

    //Ver todos los productos
    @Test
    void findAll() throws Exception {
        //System.out.println("Hola Mundo");
        //http://localhost:8080/api/products

        productRepository.saveAll(List.of(
                new Product(1L, "pc", 500d),
                new Product(2L, "laptop", 450d),
                new Product(3L, "botella", 350d)
        ));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk()) //200
                .andExpect(jsonPath("$", hasSize(3))) //200
                .andExpect(jsonPath("$[0].title").value("pc"));
    }

    @Test
    void save() throws Exception {

        //Fase 1: Datos para la prueba

        //Fase 2: ejecutar metodo o funcionalidad a testear
        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "title": producto test,
                            "price": 40.3
                        }
                        """)
        ).andExpect((status().isCreated())) //201
                .andExpect((jsonPath("$.title").value("producto test")))
                .andExpect(jsonPath("$.price").value(40.3));

        //Fase 3: Comprobaciones,aserciones,expect
        Optional<Product> productOpt = productRepository.findByTitle("producto test");
        productOpt.ifPresent(product -> assertNotNull(product.getId()));

    }

    //Ver un producto

    //Crear un nuevo producto

    //Actualizar un producto

    //Borrar un producto


}
