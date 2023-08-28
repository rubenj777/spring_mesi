package com.project.mesi.controller;

import com.project.mesi.dto.ProductDto;
import com.project.mesi.dto.UserDto;
import com.project.mesi.entity.Category;
import com.project.mesi.entity.Product;
import com.project.mesi.entity.User;
import com.project.mesi.repository.CategoryRepository;
import com.project.mesi.repository.UserRepository;
import com.project.mesi.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import com.project.mesi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
public class UserController
{

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    private final UserService userService;
    private final ProductService productService;

    public UserController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    public void UserExists(UserDto userDto, BindingResult result, boolean isEditing) {
        User usernameExist = userService.findByUsername(userDto.getUsername());
        User emailExist = userService.findByEmail(userDto.getEmail());

        if (usernameExist != null)
        {
            result.rejectValue("username", null, "Un utilisateur avec ce pseudo existe déjà.");
        }

        if (emailExist != null || !Objects.equals(usernameExist.getEmail(), userDto.getEmail()))
        {
            result.rejectValue("email", null, "Un utilisateur avec cette adresse email existe déjà.");
        }
    }

    @GetMapping(value = "/subscribe")
    public String subscribeForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "sub_form";
    }

    @PostMapping(value = "/subscribe")
    public String subscribeSubmit(@Validated @ModelAttribute("user") UserDto userDto,
                                  @RequestParam MultipartFile file,
                                  BindingResult result,
                                  Model model) throws IOException {

        boolean isEditing = false;

        UserExists(userDto, result, isEditing);
        if (result.hasErrors())
        {
            model.addAttribute("user", userDto);
            return "sub_form";
        }

        userDto.setSubscriptionDate(new Date());
        userService.save(userDto, file);
        return "redirect:/";
    }

    @GetMapping(value = "/profile/{username}")
    public String getProfile(Model model,
                             @AuthenticationPrincipal UserDetails userDetails,
                             @PathVariable String username)
    {
        User user = userService.findByUsername(userDetails.getUsername());
        List<Product> productList = user.getProductList();
        model.addAttribute("user", user);
        model.addAttribute("profileUsername", username);
        model.addAttribute("productList", productList);
        return "profile";
    }

    @GetMapping(value = "/image/{id}")
    public void getProfilePic(@PathVariable Long id,
                         HttpServletResponse response) throws IOException {
        User user = userService.findOneById(id);
        response.setContentType("image/jpg");
        InputStream is = new ByteArrayInputStream(user.getProfilePicContent());
        IOUtils.copy(is, response.getOutputStream());
    }

    @GetMapping(value = "/edit/{id}")
    public String editProfile(Model model, @PathVariable Long id) {
        User user = this.userService.findOneById(id);
        model.addAttribute("user", user);
        return "edit_profile_form";
    }

    @PostMapping(value = "/edit/{id}")
    public String editSubmit(@Validated @ModelAttribute("user") UserDto userDto,
                             @RequestParam MultipartFile file,
                             @PathVariable Long id,
                             BindingResult result,
                             Model model,
                             RedirectAttributes redirectAttr) throws IOException {

        boolean isEditing = true;
        redirectAttr.addAttribute("username", userDto.getUsername());
        redirectAttr.addAttribute("userId", id);

        UserExists(userDto, result, isEditing);
        if (result.hasErrors())
        {
            model.addAttribute("user", userDto);
            return "redirect:/edit/{userId}";
        }

        userService.update(userDto, file);

        return "redirect:/profile/{username}";
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

    @GetMapping(value = "user_product")
    public String userProductList(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername());
        List<Product> productList = user.getProductList();
        // productList.get(1).get;
        model.addAttribute("user", user);
        model.addAttribute("productList", productList);
        return "user_product";
    }

    @GetMapping(value = "delete_product")
    public String deleteProduct(@RequestParam Long productId) {
        Product product = productService.findOneById(productId);
        if (product != null) {
            productService.deleteProductByIProduct(productId);
            return "user_product";
        }
        return "user_product";
    }

    @GetMapping(value = "modified_product")
    public String updateProduct(@RequestParam Long productId, Model model) {
        Product product = productService.findOneById(productId);
        model.addAttribute("product", product);
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("categoryList", categoryList);
        // categoryList.get(0).get
        return "modified_product";
    }

    @PostMapping(value = "update_product")
    public String updateProduct(@RequestParam("productId") Long productId,
            @RequestParam("name") String name,
            @RequestParam("price") int price,
            @RequestParam("description") String description,
            @RequestParam("category") Long categoryId) {
        Product product = productService.findOneById(productId);
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        Category category = categoryRepository.findByIdCategory(categoryId);
        productService.updateProduct(product.getName(), product.getPrice(),
                product.getDescription(), category, productId);
        return "user_product";
    }

}
