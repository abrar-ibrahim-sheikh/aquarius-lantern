package com.scribblesphere.aquariuslantern.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@Entity
@Table
@Data
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long postId;
    
    @Column(name = "TITLE", length = 100, nullable = false)
    private String title;

    @Column(name = "CONTENT", length = 10000, nullable = false)
    private String content;

    @Column(name = "IMG_URL")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new LinkedList<>();

    @CreationTimestamp
    @Column(name = "CREATED_DATE")
    private Instant createdOn;

    @UpdateTimestamp
    @Column(name = "UPDATED_DATE")
    private Instant updatedOn;

}
