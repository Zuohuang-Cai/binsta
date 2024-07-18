package com.example.demo.controller;

import com.example.demo.model.Blog;
import com.example.demo.model.Users;
import com.example.demo.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;

    @GetMapping("/create")
    public String createBlog(Model model) {
        model.addAttribute("blog", new Blog());
        return "Blog/create";
    }

    @PostMapping("/create")
    public String createBlog(@ModelAttribute Blog blog) {
        Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        blog.setUser(currentUser);
        blogRepository.save(blog);
        return "redirect:/";
    }

}
