package com.project.mesi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.mesi.entity.Messagerie;
import com.project.mesi.entity.Product;
import com.project.mesi.entity.User;
import com.project.mesi.repository.MessagerieRepository;
import com.project.mesi.service.ProductService;
import com.project.mesi.service.UserService;

import jakarta.annotation.Resource;

@Controller
public class MessagerieController {
    @Autowired
    MessagerieRepository messagerieRepository;

    private final ProductService productService;
    private final UserService userService;

    public MessagerieController(MessagerieRepository messagerieRepository, ProductService productService,
            UserService userService) {
        this.messagerieRepository = messagerieRepository;
        this.productService = productService;
        this.userService = userService;
    }

    @PostMapping(value = "/send_message")
    public String sendMessage(@RequestParam("customer") Long idUser, @RequestParam("seller") Long idUser1,
            @RequestParam("object") String object, @RequestParam("content") String content) {
        Messagerie message = new Messagerie();
        message.setContent(content);
        message.setIdUser(idUser);
        message.setIdUser1(idUser1);
        message.setObject(object);
        messagerieRepository.save(message);
        return "messagerie";
    }

    @GetMapping(value = "/messagerie")
    public String sellerContact(Model model, @RequestParam("senderId") Long productId,
            @AuthenticationPrincipal UserDetails userDetails) {
        Product product = productService.findOneById(productId);
        Long userId = product.getUser().getIdUser();
        User user = userService.findByUsername(userDetails.getUsername());
        List<Messagerie> incommingMessageList = messagerieRepository.findAllByIdUser1(user.getIdUser());
        List<Messagerie> sendingMessageList = messagerieRepository.findAllByIdUser(user.getIdUser());
        model.addAttribute("sellerId", userId);
        model.addAttribute("userConnected", user.getIdUser());
        model.addAttribute("messageObject", product.getName());
        model.addAttribute("incommingMessageList", incommingMessageList);
        model.addAttribute("sendingMessageList", sendingMessageList);
        return "messagerie";
    }

    @GetMapping(value = "/messagerie_access")
    public String acessMessagerie(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername());
        List<Messagerie> incommingMessageList = messagerieRepository.findAllByIdUser1(user.getIdUser());
        List<Messagerie> sendingMessageList = messagerieRepository.findAllByIdUser(user.getIdUser());
        model.addAttribute("incommingMessageList", incommingMessageList);
        model.addAttribute("sendingMessageList", sendingMessageList);
        return "messagerie";
    }

    @GetMapping(value = "/message_response")
    public String messageResponse(Model model, @RequestParam("senderId") Long senderId,
            @AuthenticationPrincipal UserDetails userDetails) {
        return "test";
    }

}
