package com.project.mesi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    private String last_name;

    private String first_name;

    private String username;

    private String password;

    private String email;

    private Date subscription_date;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name="user_role",
            joinColumns={@JoinColumn(name="id_user", referencedColumnName="id_user")},
            inverseJoinColumns={@JoinColumn(name="id_role", referencedColumnName="id_role")})
    private List<Role> roles = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    private Product product;

}
