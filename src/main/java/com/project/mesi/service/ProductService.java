package com.project.mesi.service;

import com.project.mesi.model.Product;
import com.project.mesi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public ProductService(){}

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
