package com.project.mesi.service.impl;

import com.project.mesi.dto.ProductDto;
import com.project.mesi.entity.Product;
import com.project.mesi.entity.User;
import com.project.mesi.repository.ProductRepository;
import com.project.mesi.repository.UserRepository;
import com.project.mesi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void save(ProductDto productDto,
                     @AuthenticationPrincipal UserDetails userDetails)

    {
        Product product = new Product();

        User userConnected = userRepository.findByUsername(userDetails.getUsername());
        product.setUser(userConnected);

        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategory(productDto.getCategory());
        productRepository.save(product);
    }



}
