package com.scribblesphere.aquariuslantern.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ABOUT")
    private String about;

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Post> posts = new LinkedList<>();
//
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
//    private List<Category> categories = new LinkedList<>();

}
