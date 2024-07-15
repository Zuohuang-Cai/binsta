package com.example.demo.controller;

import com.example.demo.DTO.UserRegisterDTO;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute UserRegisterDTO userRegisterDTO) {
        ModelAndView modelAndView = new ModelAndView("login");
        try {
            userService.registerUser(userRegisterDTO);
            modelAndView.addObject("success", "User registered successfully, please login");
            return modelAndView;
        } catch (Exception e) {
            modelAndView.addObject("error", e.getMessage());
            return modelAndView;
        }
    }
}
