package com.project.mesi.controller;

import com.project.mesi.dto.UserDto;
import com.project.mesi.entity.Product;
import com.project.mesi.entity.User;
import com.project.mesi.repository.CategoryRepository;
import com.project.mesi.repository.UserRepository;
import com.project.mesi.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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

    public UserController(UserService userService) {
        this.userService = userService;
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
    public String subscribeForm(Model model)
    {
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

}
