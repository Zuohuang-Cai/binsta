package com.example.demo.seeder;

import com.example.demo.model.Blog;
import com.example.demo.model.Users;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.UsersRepository;
import com.example.demo.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


@Component
public class BlogSeeder {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UsersRepository userRepository;

    private static final List<String> TITLES = Arrays.asList(
            "Exploring the Universe", "A Day in the Life", "Nature's Beauty", "Tech Innovations",
            "Historical Insights", "Culinary Adventures", "Travel Diaries", "Fitness Routines",
            "Music and Art", "Gaming Trends"
    );

    private static final List<String> DESCRIPTIONS = Arrays.asList(
            "An in-depth look at the cosmos.", "A snapshot of daily life.", "Capturing the essence of nature.",
            "The latest in technology.", "Exploring historical events.", "Tasting the world's flavors.",
            "Journeys around the globe.", "Staying fit and healthy.", "Expressions through music and art.",
            "What's new in gaming."
    );

    private static final List<String> IMAGES = Arrays.asList(
            "mountain.jpeg", "space.jpeg", "pinkBol.png", "sunflower.png", "rat.png", "panda.png", "tiger.png"
    );

    public void createRandomBlog() {
        Random random = new Random();

        String title = TITLES.get(random.nextInt(TITLES.size()));
        String description = DESCRIPTIONS.get(random.nextInt(DESCRIPTIONS.size()));
        String imageFileName = IMAGES.get(random.nextInt(IMAGES.size()));
        byte[] image = FileUtils.readImage("src/main/resources/static/images/" + imageFileName);

        Users user = getRandomUser();

        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setUser(user);
        blog.setDescription(description);
        blog.setCodes(FileUtils.readText("src/main/resources/static/images/Seeder.md"));
        blog.setImage(image);
        blogRepository.save(blog);
    }

    public void seedData() {
        Blog blog1 = new Blog();
        blog1.setTitle("picture of mountain !");
        blog1.setUser(userRepository.findByUsername("user").orElseThrow(() -> new RuntimeException("User not found at BlogSeeder")));
        blog1.setDescription("this mountain looks huge");
        blog1.setImage(FileUtils.readImage("src/main/resources/static/images/mountain.jpeg"));
        blog1.setCodes(FileUtils.readText("src/main/resources/static/images/Seeder.md"));

        blogRepository.save(blog1);

        Blog blog2 = new Blog();
        blog2.setTitle("picture of space !");
        blog2.setUser(userRepository.findByUsername("user").orElseThrow(() -> new RuntimeException("User not found at BlogSeeder")));
        blog2.setDescription("this space looks beautiful");
        blog2.setCodes(FileUtils.readText("src/main/resources/static/images/Seeder.md"));
        blog2.setImage(FileUtils.readImage("src/main/resources/static/images/space.jpeg"));
        blogRepository.save(blog2);
    }

    private Users getRandomUser() {
        List<Users> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new RuntimeException("No users found");
        }
        Random random = new Random();
        return users.get(random.nextInt(users.size()));
    }

}