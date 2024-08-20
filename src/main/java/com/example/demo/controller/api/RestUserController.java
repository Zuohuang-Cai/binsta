package com.example.demo.controller.api;

import com.example.demo.DTO.CurrentUserLikesDTO;
import com.example.demo.DTO.GetCurrentUserDTO;
import com.example.demo.DTO.Result;
import com.example.demo.model.BlogLikes;
import com.example.demo.model.Users;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RestUserController {
    @Autowired
    private UserService userService;

    @GetMapping("/current-user")
    public GetCurrentUserDTO getCurrentUserId() {
        return userService.getLoggedInUserDTO();
    }

    @GetMapping("/search-user")
    public Result searchUser(@RequestParam String username) {
        List<Users> users = userService.findUsersByUsernamePrefix(username);
        if (users.isEmpty() || username.isEmpty()) {
            return Result.fail("User not found");
        }
        return Result.success(users.stream().map(user -> GetCurrentUserDTO.builder()
                .username(user.getUsername())
                .nickname(user.getNickname())
                .avatar(user.getAvatarBase64())
                .build()).collect(Collectors.toList()));
    }

    @GetMapping("/current-user-likes")
    public Result getCurrentUserLikes() {
        List<CurrentUserLikesDTO> a = userService.getLoggedInUser().getBlogLikes().stream()
                .map(blogLikes -> CurrentUserLikesDTO.builder()
                        .title(blogLikes.getBlog().getTitle())
                        .blogId(blogLikes.getBlog().getId())
                        .description(blogLikes.getBlog().getDescription())
                        .build())
                .collect(Collectors.toList());

        return Result.success(a);
    }

}
