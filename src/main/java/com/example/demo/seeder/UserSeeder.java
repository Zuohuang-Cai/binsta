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
import java.util.List;

@Component
public class UserSeeder {
    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void seedData() {
        Users user1 = new Users();
        user1.setUsername("user");
        user1.setPassword(passwordEncoder.encode("user"));
        user1.setRoles(List.of(UserRole.USER));
        user1.setNickname("nickname of user");
        user1.setAvatar(FileUtils.readImage("src/main/resources/static/images/AvatarKids.png"));
        userRepository.save(user1);

        Users user2 = new Users();
        user2.setUsername("admin");
        user2.setPassword(passwordEncoder.encode("admin"));
        user2.setRoles(List.of(UserRole.ADMIN));
        user2.setNickname("nickname of admin");
        user2.setAvatar(FileUtils.readImage("src/main/resources/static/images/AvatarMan.png"));
        userRepository.save(user2);


        Users user3 = new Users();
        user3.setUsername("user2");
        user3.setPassword(passwordEncoder.encode("user2"));
        user3.setRoles(List.of(UserRole.USER));
        user3.setNickname("nickname of user2");
        user3.setAvatar(FileUtils.readImage("src/main/resources/static/images/AvatarMan.png"));
        userRepository.save(user3);

        Users user4 = new Users();
        user4.setUsername("useradmin");
        user4.setPassword(passwordEncoder.encode("useradmin"));
        user4.setRoles(List.of(UserRole.USER, UserRole.ADMIN));
        user4.setNickname("nickname of useradmin");
        user4.setAvatar(FileUtils.readImage("src/main/resources/static/images/AvatarKids.png"));
        userRepository.save(user4);

    }
}
