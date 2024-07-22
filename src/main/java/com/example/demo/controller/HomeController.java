package com.example.demo.controller;

import com.example.demo.services.BlogService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.format.DateTimeFormatter;
import java.util.Base64;

@Controller
public class HomeController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;

    @GetMapping({"/", "index"})
    public String index(Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        model.addAttribute("formatter", formatter);
        model.addAttribute("blogsData", blogService.getRandomBlogs());
        model.addAttribute("UserAvatar", Base64.getEncoder().encodeToString(userService.getLoggedInUser().getAvatar()));
        return "index";
    }
}
