package com.scribblesphere.aquariuslantern.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @Column(name = "TITLE", length = 100, nullable = false)
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

}
