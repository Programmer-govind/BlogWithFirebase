package com.example.LoginPlusSecurity.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "MEDIUMTEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private String authorName;

    // Images
    @ElementCollection
    @CollectionTable(name = "blog_images", joinColumns = @JoinColumn(name = "blog_id"))
    @Column(name = "image_url")
    private List<String> imageUrls = new ArrayList<>();

    @Column(nullable = false, unique = true)
    private String slug;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private int views = 0;

    public String getExcerpt() {
        String[] words = content.split("\\s+");
        return words.length > 50 ? String.join(" ", java.util.Arrays.copyOf(words, 50)) + "..." : content;
    }
}
