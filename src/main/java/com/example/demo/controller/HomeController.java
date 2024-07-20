package com.example.demo.controller;

import com.example.demo.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    @Autowired
    private BlogService blogService;

    @GetMapping({"/", "index"})
    public String index(Model model) {
        model.addAttribute("blogsData", blogService.getRandomBlogs());
        return "index";
    }
}
