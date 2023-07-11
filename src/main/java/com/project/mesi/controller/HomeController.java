package com.project.mesi.controller;

import com.project.mesi.entity.Product;
import com.project.mesi.repository.ProductRepository;
import com.project.mesi.service.ProductService;
import com.project.mesi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    ProductRepository productRepository;

    @GetMapping(value = "/")
    public String home(Model model) {
        List<Product> productList = productRepository.findAll();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", auth.getName());
        model.addAttribute("productList", productList);
        return "index";
    }

}
