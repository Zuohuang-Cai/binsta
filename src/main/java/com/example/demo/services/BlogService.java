package com.example.demo.services;

import com.example.demo.DTO.CreateBlogDTO;
import com.example.demo.DTO.ShowBlogDTO;
import com.example.demo.model.Blog;
import com.example.demo.model.BlogLikes;
import com.example.demo.model.Commit;
import com.example.demo.model.Users;
import com.example.demo.repository.BlogLikesRepository;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.CommitRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
    private final BlogLikesRepository blogLikesRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository, UserService userService, CommitRepository commitRepository, BlogLikesRepository blogLikesRepository) {
        this.blogRepository = blogRepository;
        this.userService = userService;
        this.blogLikesRepository = blogLikesRepository;
        this.commitRepository = commitRepository;
    }


    @Autowired
    @Qualifier("BCryptpasswordEncoder")
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void addLike(Long blogId) {

        BlogLikes blogLikes = BlogLikes.builder()
                .blog(blogRepository.findById(blogId).orElseThrow(() -> new RuntimeException("Blog not found")))
                .user(userService.getLoggedInUser())
                .build();
        blogLikesRepository.save(blogLikes);
    }

    @Transactional
    public void deleteLike(Long blogId) {
        Users loggedInUser = userService.getLoggedInUser();

        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog not found"));

        BlogLikes blogLikes = blogLikesRepository.findByBlogAndUser(blog, loggedInUser)
                .orElseThrow(() -> new RuntimeException("Like not found"));

        blogLikesRepository.delete(blogLikes);
    }


    @Transactional
    public void createBlog(CreateBlogDTO blog) throws IOException {
        Blog blogModel = new Blog();
        blogModel.setTitle(blog.getTitle());
        blogModel.setDescription(blog.getDescription());
        blogModel.setImage(blog.getImage().getBytes());
        blogModel.setUser(userService.getLoggedInUser());
        blogModel.setCodes(new String(blog.getCodes().getBytes(), StandardCharsets.UTF_8));
        blogRepository.save(blogModel);
    }

    @Transactional
    public void createCommit(Long blogId, String description) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog not found"));

        if (description.isEmpty()) {
            throw new RuntimeException("Description cannot be empty");
        }
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
            System.out.println(blog.getBlogCommits());
            System.out.println(blog.getBlogLikes());
            ShowBlogDTO showBlogDTO = ShowBlogDTO.builder()
                    .id(blog.getId())
                    .title(blog.getTitle())
                    .description(blog.getDescription())
                    .imageBase64(blog.GetImageBase64())
                    .likeCount(blog.getLikeCount())
                    .createDate(blog.getCreateDate().format(formatter))
                    .nickName(blog.getUser().getNickname())
                    .avatarBase64(blog.getUser().getAvatarBase64())
                    .commits(blog.getBlogCommits())
                    .codes(blog.getCodes())
                    .build();

            showBlogDTOS.add(showBlogDTO);
        }

        return showBlogDTOS;
    }
}
