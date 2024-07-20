package com.example.demo.services;

import com.example.demo.DTO.CreateBlogDTO;
import com.example.demo.DTO.ShowBlogDTO;
import com.example.demo.model.Blog;
import com.example.demo.repository.BlogRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.type.descriptor.DateTimeUtils;
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
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    UserService userService;

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

    public List<ShowBlogDTO> getRandomBlogs() {
        List<Blog> allBlogs = StreamSupport.stream(blogRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Collections.shuffle(allBlogs);
        List<Blog> blogs = allBlogs.subList(0, Math.min(allBlogs.size(), 10));
        List<ShowBlogDTO> showBlogDTOS = new ArrayList<>();
        for (Blog blog : blogs) {
            ShowBlogDTO showBlogDTO = ShowBlogDTO.builder()
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
