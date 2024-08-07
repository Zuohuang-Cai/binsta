package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "Blog")
@Data
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "LONGBLOB")
    @Lob
    private byte[] image;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String codes;

    @Column(nullable = false)
    private String description;
    @Column(name = "create_date", updatable = false, nullable = false)
    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;
    @JsonIgnore
    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Commit> blogCommits;
    @JsonIgnore
    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BlogLikes> blogLikes;

    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDateTime.now();
    }

    public String GetImageBase64() {
        return Base64.getEncoder().encodeToString(this.image);
    }

    public int getLikeCount() {
        return this.blogLikes.size();
    }

    @Override
    public String toString() {
        return "";
    }
}
