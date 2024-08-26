package com.example.demo.seeder;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class SeederManager {
    private final UserSeeder userSeeder;
    private final BlogSeeder blogSeeder;
    private final CommitSeeder commitSeeder;

    @Autowired
    public SeederManager(UserSeeder userSeeder, BlogSeeder blogSeeder, CommitSeeder commitSeeder) {
        this.userSeeder = userSeeder;
        this.blogSeeder = blogSeeder;
        this.commitSeeder = commitSeeder;
    }

    @PostConstruct
    public void seedData() {
        userSeeder.seedData();
        blogSeeder.seedData();
        for (int i = 0; i < 20; i++) {
            userSeeder.createRandomUser();
        }
        for (int i = 0; i < 10; i++) {
            blogSeeder.createRandomBlog();
        }
        for (int i = 0; i < 15; i++) {
            commitSeeder.seedData();
        }
    }
}
