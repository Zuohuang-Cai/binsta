package com.example.demo.repository;

import com.example.demo.model.Blog;
import com.example.demo.model.BlogLikes;
import com.example.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BlogLikesRepository extends JpaRepository<BlogLikes, Long> {
    Optional<BlogLikes> findByBlogAndUser(Blog blog, Users loggedInUser);
}
