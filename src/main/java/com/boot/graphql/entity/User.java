package com.boot.graphql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String userName;
    private String email;
    private String phone;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true ,  mappedBy = "user")
    List<Order> orders = new ArrayList<>();

}
