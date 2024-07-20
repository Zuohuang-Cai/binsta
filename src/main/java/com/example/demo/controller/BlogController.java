package com.example.demo.controller;

import com.example.demo.DTO.CreateBlogDTO;
import com.example.demo.model.Blog;
import com.example.demo.repository.BlogRepository;
import com.example.demo.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private BlogService blogService;

    @GetMapping("/create")
    public String createBlog(Model model) {
        model.addAttribute("blog", new CreateBlogDTO());
        return "Blog/create";
    }

    @PostMapping("/create")
    public String createBlog(@ModelAttribute CreateBlogDTO blog) throws IOException {
        blogService.createBlog(blog);
        return "redirect:/";
    }

}
