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

    @Autowired
    public SeederManager(UserSeeder userSeeder, BlogSeeder blogSeeder) {
        this.userSeeder = userSeeder;
        this.blogSeeder = blogSeeder;
    }

    @PostConstruct
    public void seedData() {
        userSeeder.seedData();
        blogSeeder.seedData();
    }
}
