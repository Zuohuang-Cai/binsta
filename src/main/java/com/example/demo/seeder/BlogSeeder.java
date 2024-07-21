package com.example.demo.seeder;

import com.example.demo.enums.UserRole;
import com.example.demo.model.Blog;
import com.example.demo.model.Users;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.UsersRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class BlogSeeder {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UsersRepository userRepository;

    public void seedData() {
        Blog blog1 = new Blog();
        blog1.setTitle("picture of mountain !");
        blog1.setUser(userRepository.findByUsername("user").orElseThrow(() -> new RuntimeException("User not found at BlogSeeder")));
        blog1.setDescription("this mountain looks huge");
        blog1.setImage(readImage("src/main/resources/static/images/mountain.jpeg"));
        blogRepository.save(blog1);

        Blog blog2 = new Blog();
        blog2.setTitle("picture of space !");
        blog2.setUser(userRepository.findByUsername("user").orElseThrow(() -> new RuntimeException("User not found at BlogSeeder")));
        blog2.setDescription("this space looks beautiful");
        blog2.setImage(readImage("src/main/resources/static/images/space.jpeg"));
        blogRepository.save(blog2);
    }

    private byte[] readImage(String path) {
        try {
            return Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Error reading image");
    }
}