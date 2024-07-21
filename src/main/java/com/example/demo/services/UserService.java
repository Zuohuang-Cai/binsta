package com.example.demo.services;

import com.example.demo.DTO.UserRegisterDTO;
import com.example.demo.enums.UserRole;
import com.example.demo.model.Users;
import com.example.demo.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    @Qualifier("BCryptpasswordEncoder")
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void registerUser(UserRegisterDTO userRegisterDTO) {
        if (usersRepository.findByUsername(userRegisterDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        log.info(passwordEncoder.encode(userRegisterDTO.getPassword()));
        Users user = new Users();
        user.setUsername(userRegisterDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        List<UserRole> roles = userRegisterDTO.getRoles().stream()
                .map(UserRole::valueOf)
                .collect(Collectors.toList());
        user.setRoles(roles);
        usersRepository.save(user);
    }

    public Users getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("cant find Username in UserService");
        }

        Object principal = authentication.getPrincipal();

        return usersRepository.findByUsername(((UserDetails) principal).getUsername()).orElseThrow(
                () -> new RuntimeException("cant find Username in UserService")
        );
    }
}
