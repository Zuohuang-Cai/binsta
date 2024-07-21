package com.example.demo.repository;

import com.example.demo.model.BlogLikes;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BlogLikesRepository extends JpaRepository<BlogLikes, Long> {
}
