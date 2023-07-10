package com.project.mesi.controller;

import com.project.mesi.dto.UserDto;
import com.project.mesi.entity.User;
import com.project.mesi.repository.UserRepository;
import com.project.mesi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;


@Controller
public class UserController
{

    @Autowired
    UserRepository userRepository;
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/subscribe")
    public String subscribeForm(Model model)
    {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "sub_form";
    }

    @PostMapping(value = "/subscribe")
    public String subscribeSubmit(@Validated @ModelAttribute("user") UserDto user,
                                  BindingResult result,
                                  Model model)
    {
        User usernameExist = userService.findByUsername(user.getUsername());
        User emailExist = userService.findByEmail(user.getEmail());

        if (usernameExist != null)
        {
            result.rejectValue("username", null, "Un utilisateur avec ce pseudo existe déjà.");
        }

        if (emailExist != null)
        {
            result.rejectValue("email", null, "Un utilisateur avec cette adresse email existe déjà.");
        }

        if (result.hasErrors())
        {
            model.addAttribute("user", user);
            return "sub_form";
        }

        user.setSubscription_date(new Date());
        userService.save(user);
        return "redirect:/";
    }

}
