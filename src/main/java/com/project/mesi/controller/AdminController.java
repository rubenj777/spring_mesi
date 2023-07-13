package com.project.mesi.controller;

import com.project.mesi.dto.CategoryDto;
import com.project.mesi.entity.Category;
import com.project.mesi.entity.User;
import com.project.mesi.repository.UserRepository;
import com.project.mesi.service.CategoryService;
import com.project.mesi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    UserRepository userRepository;

    private final UserService userService;
    private final CategoryService categoryService;

    public AdminController(UserService userService, CategoryService categoryService) {
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/all_users")
    public String getAllUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "all_users_list";
    }

    @GetMapping(value = "/delete_user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(userService.findOneById(id));
        return "redirect:/all_users";
    }

    @GetMapping(value = "/add_category")
    public String addCategoryForm(Model model) {
        CategoryDto categoryDto = new CategoryDto();
        model.addAttribute("category", categoryDto);
        return "category_form";
    }

    @PostMapping(value = "/add_category")
    public String subscribeSubmit(@Validated @ModelAttribute("category") CategoryDto categoryDto,
                                  BindingResult result,
                                  Model model)
    {
        Category categoryExist = categoryService.findByName(categoryDto.getName());

        if (categoryExist != null)
        {
            result.rejectValue("name", null, "Une catégorie porte déjà ce nom");
        }

        if (result.hasErrors())
        {
            model.addAttribute("category", categoryDto);
            return "category_form";
        }

        categoryService.save(categoryDto);
        return "redirect:/";
    }

}
