package com.example.demo.services;

import com.example.demo.DTO.CreateBlogDTO;
import com.example.demo.DTO.ShowBlogDTO;
import com.example.demo.model.Blog;
import com.example.demo.model.Commit;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.CommitRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class BlogService {
    private final BlogRepository blogRepository;
    private final UserService userService;
    private final CommitRepository commitRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository, UserService userService, CommitRepository commitRepository) {
        this.blogRepository = blogRepository;
        this.userService = userService;
        this.commitRepository = commitRepository;
    }


    @Autowired
    @Qualifier("BCryptpasswordEncoder")
    private PasswordEncoder passwordEncoder;


    public void createBlog(CreateBlogDTO blog) throws IOException {
        Blog blogModel = new Blog();
        blogModel.setTitle(blog.getTitle());
        blogModel.setDescription(blog.getDescription());
        blogModel.setImage(blog.getImage().getBytes());
        blogModel.setUser(userService.getLoggedInUser());
        blogRepository.save(blogModel);
    }

    @Transactional
    public void createCommit(Long blogId, String description) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog not found"));

        Commit commit = Commit.builder()
                .description(description)
                .user(userService.getLoggedInUser())
                .blog(blog)
                .build();
        commitRepository.save(commit);
    }


    public List<ShowBlogDTO> getRandomBlogs() {
        List<Blog> allBlogs = StreamSupport.stream(blogRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Collections.shuffle(allBlogs);
        List<Blog> blogs = allBlogs.subList(0, Math.min(allBlogs.size(), 10));
        List<ShowBlogDTO> showBlogDTOS = new ArrayList<>();
        for (Blog blog : blogs) {
            ShowBlogDTO showBlogDTO = ShowBlogDTO.builder()
                    .id(blog.getId())
                    .title(blog.getTitle())
                    .description(blog.getDescription())
                    .imageBase64(blog.GetImageBase64())
                    .likeCount(blog.getLikeCount())
                    .createDate(blog.getCreateDate().format(formatter))
                    .username(blog.getUser().getUsername())
                    .build();

            showBlogDTOS.add(showBlogDTO);
        }

        return showBlogDTOS;
    }
}
