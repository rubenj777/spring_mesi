package com.project.mesi.controller;

import com.project.mesi.dto.ProductDto;
import com.project.mesi.entity.Category;
import com.project.mesi.entity.Product;
import com.project.mesi.repository.CategoryRepository;
import com.project.mesi.repository.ProductRepository;
import com.project.mesi.service.ProductService;
import com.project.mesi.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("products")
public class ProductController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    ProductRepository productRepository;

    public ProductController(CategoryRepository categoryRepository, ProductService productService) {
        this.productService = productService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping(value = "/add_product")
    public String addProductForm(Model model) {
        List<Category> categoryList = categoryRepository.findAll();
        ProductDto productDto = new ProductDto();
        model.addAttribute("product", productDto);
        model.addAttribute("categoryList", categoryList);
        return "add_product_form";
    }

    @PostMapping(value = "/save_product")
    public String saveProduct(@Validated @ModelAttribute("product") ProductDto productDto,
            @AuthenticationPrincipal UserDetails user,
            @RequestParam MultipartFile file) throws IOException {

        productService.save(productDto, user, file);

        return "redirect:/";
    }

    @GetMapping
    public String getProducts(Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("name") Optional<String> name) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        if (name.isPresent()) {
            Page<Product> pagination = productService.findPaginated(currentPage, pageSize, name.get());
            List<Product> productList = pagination.getContent();
            model.addAttribute("productList", productList);
        } else {
            Page<Product> pagination = productService.findPaginated(currentPage, pageSize);
            List<Product> productList = pagination.getContent();
            model.addAttribute("productList", productList);
        }

        List<Category> categoryList = categoryRepository.findAll();

        model.addAttribute("size", pageSize);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("categoryList", categoryList);

        return "products";
    }

    @GetMapping(value = "/image/{id}")
    public void getImage(@PathVariable Long id,
            HttpServletResponse response) throws IOException {
        Product product = productService.findOneById(id);
        response.setContentType("image/jpg");
        InputStream is = new ByteArrayInputStream(product.getFileContent());
        IOUtils.copy(is, response.getOutputStream());
    }

}
