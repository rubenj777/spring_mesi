package com.project.mesi.controller;

import com.project.mesi.dto.CategoryDto;
import com.project.mesi.dto.ProductDto;
import com.project.mesi.entity.Category;
import com.project.mesi.repository.CategoryRepository;
import com.project.mesi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    CategoryRepository categoryRepository;

    ProductService productService;

    public ProductController(CategoryRepository categoryRepository, ProductService productService) {
        this.productService = productService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping(value = "/add_product")
    public String addProductForm(Model model)
    {
        List<Category> categoryList = categoryRepository.findAll();
        ProductDto productDto = new ProductDto();
        model.addAttribute("product", productDto);
        model.addAttribute("categoryList", categoryList);
        return "add_product_form";
    }

    @PostMapping(value = "/add_product")
    public String addProduct(@Validated @ModelAttribute("product") ProductDto productDto,
                             @ModelAttribute CategoryDto categoryDto,
                             @AuthenticationPrincipal UserDetails user)
    {

        productService.save(productDto, user);

        return "redirect:/";
    }

}
