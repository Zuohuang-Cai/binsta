package com.example.demo.DTO;

import com.example.demo.model.Commit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ShowBlogDTO {
    private Long id;
    private String title;
    private String imageBase64;
    private String description;
    private String avatarBase64;
    private int likeCount;
    private String createDate;
    private String nickName;
    private List<Commit> commits;
}
