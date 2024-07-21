package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ShowBlogDTO {
    private Long id;
    private String title;
    private String imageBase64;
    private String description;
    private int likeCount;
    private String createDate;
    private String username;
}
