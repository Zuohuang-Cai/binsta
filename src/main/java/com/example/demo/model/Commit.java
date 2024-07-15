package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "commit")
public class Commit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(nullable = false)
    private String description;

    @Column(name = "create_date", updatable = false, nullable = false)
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "commit", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BlogCommit> blogCommits;

    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDateTime.now();
    }
}
