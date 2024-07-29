package com.example.demo.controller.api;

import com.example.demo.DTO.GetCurrentUserDTO;
import com.example.demo.model.Users;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestUserController {
    @Autowired
    private UserService userService;

    @GetMapping("/current-user")
    public GetCurrentUserDTO getCurrentUserId() {
        return userService.getLoggedInUserDTO();
    }

}
