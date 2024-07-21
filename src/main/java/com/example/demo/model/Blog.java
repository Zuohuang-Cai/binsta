package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Base64;
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
    @Column(nullable = false)
    private String description;
    @Column(name = "create_date", updatable = false, nullable = false)
    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Commit> blogCommits;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BlogLikes> blogLikes;

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
}
