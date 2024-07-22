package com.example.demo.seeder;

import com.example.demo.model.Blog;
import com.example.demo.model.Commit;
import com.example.demo.model.Users;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.CommitRepository;
import com.example.demo.repository.UsersRepository;
import com.example.demo.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class CommitSeeder {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private CommitRepository commitRepository;

    public void seedData() {
        blogRepository.findAll().forEach(blog -> {
            Commit commit = Commit.builder()
                    .blog(blog)
                    .user(this.getRandomUser())
                    .description(this.getRandomDescription())
                    .build();
            commitRepository.save(commit);
        });
    }

    private String getRandomDescription() {
        List<String> descriptions = Arrays.asList(
                "This is a great post!",
                "I totally agree with you.",
                "Well said!",
                "Thank you for sharing.",
                "Interesting perspective.",
                "I learned something new today.",
                "This is very insightful.",
                "I appreciate your point of view.",
                "Nicely written!",
                "I couldn't agree more."
        );

        Random random = new Random();
        int index = random.nextInt(descriptions.size());
        return descriptions.get(index);
    }

    private Users getRandomUser() {
        List<Users> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new RuntimeException("No users found at CommitSeeder");
        }

        Random random = new Random();
        int index = random.nextInt(users.size());
        return users.get(index);
    }

}