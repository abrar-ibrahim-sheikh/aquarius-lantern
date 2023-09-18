package com.scribblesphere.aquariuslantern.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;

    @NotEmpty
    @Column(name = "NAME")
    private String name;

    @NotEmpty
    @Size(min = 4, max = 16, message = "Should contain 4 Character or max of 16")
    @Column(name = "PASSWORD")
    private String password;

    @Email(message = "Not a valid email")
    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ABOUT")
    private String about;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "roleId"))
    private Set<Role> roles = new HashSet<>();

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Post> posts = new LinkedList<>();
//
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
//    private List<Category> categories = new LinkedList<>();

}
