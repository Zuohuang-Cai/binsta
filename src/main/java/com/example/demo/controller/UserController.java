package com.example.demo.controller;

import com.example.demo.DTO.EditUserDTO;
import com.example.demo.DTO.Result;
import com.example.demo.DTO.UserRegisterDTO;
import com.example.demo.enums.UserRole;
import com.example.demo.model.Users;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login() {
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model) {
        UserRole[] roles = UserRole.values();
        model.addAttribute("roles", roles);
        return "register";
    }

    @GetMapping("/profile")
    public String profile(@RequestParam(name = "username", required = true) String username, Model model) {
        model.addAttribute("user", userService.findByUsername(username));
        return "profile";
    }

    @GetMapping("/edit")
    public String editProfile(Model model) {
        Users user = userService.getLoggedInUser();
        model.addAttribute("editUserDTO", EditUserDTO.builder()
                .bio(user.getBio())
                .nickname(user.getNickname())
                .build());
        return "edit";
    }

    @PostMapping("/edit")
    public String editUser(EditUserDTO editUserDTO) throws IOException {
        userService.UpdateUser(editUserDTO);
        return "redirect:/user/profile?username=" + userService.getLoggedInUser().getUsername();
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
