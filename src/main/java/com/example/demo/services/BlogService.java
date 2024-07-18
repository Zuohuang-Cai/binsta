package com.example.demo.services;

import com.example.demo.model.Blog;
import com.example.demo.repository.BlogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    UserService userService;

    @Autowired
    @Qualifier("BCryptpasswordEncoder")
    private PasswordEncoder passwordEncoder;


    public void createBlog(Blog blog) {
        blog.setUser(userService.getLoggedInUser());
        blogRepository.save(blog);
    }

    public List<Blog> getRandomBlogs() {
        List<Blog> allBlogs = StreamSupport.stream(blogRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        Collections.shuffle(allBlogs);

        return allBlogs.subList(0, Math.min(allBlogs.size(), 10));
    }
}
