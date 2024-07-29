package com.example.demo.seeder;

import com.example.demo.enums.UserRole;
import com.example.demo.model.Users;
import com.example.demo.repository.UsersRepository;
import com.example.demo.utils.FileUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class UserSeeder {
    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final List<String> NICKNAMES = Arrays.asList(
            "Cool Kid", "Adventure Seeker", "Tech Guru", "Book Lover", "Fitness Enthusiast",
            "Music Fan", "Foodie", "Traveler", "Gamer", "Artist"
    );
    private static final List<String> Avatars = Arrays.asList(
            "AvatarKids.png", "AvatarMan.png", "AvatarWoman.png"
    );

    private static final List<String> ROLES = Arrays.asList(
            "USER", "ADMIN"
    );


    public void seedData() {
        Users user1 = new Users();
        user1.setUsername("user");
        user1.setPassword(passwordEncoder.encode("user"));
        user1.setRoles(List.of(UserRole.USER));
        user1.setNickname("nickname of user, photo = kid");
        user1.setAvatar(FileUtils.readImage("src/main/resources/static/images/AvatarKids.png"));
        userRepository.save(user1);

        Users user2 = new Users();
        user2.setUsername("admin");
        user2.setPassword(passwordEncoder.encode("admin"));
        user2.setRoles(List.of(UserRole.ADMIN));
        user2.setNickname("nickname of admin ,photo = Man");
        user2.setAvatar(FileUtils.readImage("src/main/resources/static/images/AvatarMan.png"));
        userRepository.save(user2);


        Users user3 = new Users();
        user3.setUsername("user2");
        user3.setPassword(passwordEncoder.encode("user2"));
        user3.setRoles(List.of(UserRole.USER));
        user3.setNickname("nickname of user2 , photo = women");
        user3.setAvatar(FileUtils.readImage("src/main/resources/static/images/AvatarWoman.png"));
        userRepository.save(user3);

        Users user4 = new Users();
        user4.setUsername("useradmin");
        user4.setPassword(passwordEncoder.encode("useradmin"));
        user4.setRoles(List.of(UserRole.USER, UserRole.ADMIN));
        user4.setNickname("nickname of useradmin , photo = kid");
        user4.setAvatar(FileUtils.readImage("src/main/resources/static/images/AvatarKids.png"));
        userRepository.save(user4);

    }

    public void createRandomUser() {
        Random random = new Random();

        String username = generateUniqueUsername();

        String password = "password";
        UserRole role = UserRole.valueOf(ROLES.get(random.nextInt(ROLES.size())));

        String nickname = NICKNAMES.get(random.nextInt(NICKNAMES.size()));

        byte[] avatar = FileUtils.readImage("src/main/resources/static/images/" +
                Avatars.get(random.nextInt(Avatars.size())));

        Users user = new Users();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(List.of(role));
        user.setNickname(nickname);
        user.setAvatar(avatar);

        userRepository.save(user);
    }

    private String generateUniqueUsername() {
        String baseUsername;
        String username;

        Random random = new Random();
        do {
            baseUsername = "user" + UUID.randomUUID().toString().substring(0, 6);
            username = baseUsername;
        } while (userRepository.existsByUsername(username));

        return username;
    }
}
