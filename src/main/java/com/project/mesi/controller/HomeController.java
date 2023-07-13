package com.project.mesi.controller;

import com.project.mesi.entity.Product;
import com.project.mesi.repository.ProductRepository;
import com.project.mesi.service.ProductService;
import com.project.mesi.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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
        model.addAttribute("productList", productList);
        return "index";
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
