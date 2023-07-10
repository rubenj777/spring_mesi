package com.project.mesi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_product;

    private String name;

    private int price;

    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @JoinTable(name = "user_product",
        joinColumns =
                { @JoinColumn(name = "id_product", referencedColumnName = "id_product") },
        inverseJoinColumns =
                { @JoinColumn(name = "id_user", referencedColumnName = "id_user") })
    private User user;

}
