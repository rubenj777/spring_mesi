package com.project.mesi.dto;

import com.project.mesi.entity.Category;
import com.project.mesi.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;

    private String name;

    private int price;

    private String description;

    private User user;

    private Category category;

}
