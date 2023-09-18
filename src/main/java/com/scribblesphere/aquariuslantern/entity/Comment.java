package com.scribblesphere.aquariuslantern.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column
    private String content;

    @ManyToOne
    private Post post;
}
