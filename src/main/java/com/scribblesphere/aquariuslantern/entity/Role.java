package com.scribblesphere.aquariuslantern.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @Column(name = "role_name")
    private String name;

//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users;
}
