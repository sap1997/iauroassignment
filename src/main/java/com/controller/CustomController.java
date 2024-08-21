package com.iauro.controller;

import com.iauro.model.Product;
import com.iauro.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> listProducts() {
        return productRepository.findByVisibleToCustomersTrue();
    }
}
