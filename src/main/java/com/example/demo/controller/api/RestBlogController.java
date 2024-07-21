package com.example.demo.controller.api;

import com.example.demo.DTO.CreateBlogDTO;
import com.example.demo.DTO.ShowBlogDTO;
import com.example.demo.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestBlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/random-blog")
    public List<ShowBlogDTO> randomBlog() {
        return blogService.getRandomBlogs();
    }



}
