package com.example.demo.controller;

import com.example.demo.DTO.CreateBlogDTO;
import com.example.demo.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/create")
    public String createBlog(Model model) {
        model.addAttribute("blog", new CreateBlogDTO());
        return "Blog/create";
    }

    @PostMapping("/commit")
    public String createCommit(@RequestParam Long blogId, @RequestParam String description) {
        blogService.createCommit(blogId, description);
        return "redirect:/";
    }

    @PostMapping("/create")
    public String createBlog(@ModelAttribute CreateBlogDTO blog) throws IOException {
        blogService.createBlog(blog);
        return "redirect:/";
    }

}
