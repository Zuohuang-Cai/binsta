package com.example.demo.controller.api;

import com.example.demo.DTO.CreateBlogDTO;
import com.example.demo.DTO.Result;
import com.example.demo.DTO.ShowBlogDTO;
import com.example.demo.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestBlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/random-blog")
    public List<ShowBlogDTO> randomBlog() {
        return blogService.getRandomBlogs();
    }

    @PostMapping("/blog/commit")
    public Result createCommit(@RequestParam Long blogId, @RequestParam String description) {
        blogService.createCommit(blogId, description);
        return Result.success();
    }

    @PostMapping(value = "/blog/like", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result addLike(@RequestParam Long blogId) {
        blogService.addLike(blogId);
        return Result.success();
    }

    @DeleteMapping(value = "/blog/like", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result deleteLike(@RequestParam Long blogId) {
        blogService.deleteLike(blogId);
        return Result.success();
    }

}
