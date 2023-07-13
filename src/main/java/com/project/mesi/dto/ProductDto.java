package com.project.mesi.dto;

import com.project.mesi.entity.Category;
import com.project.mesi.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;

    private String name;

    private int price;

    private String description;

    private byte[] fileContent;

    private String filePath;

    private User user;

    private Category category;

}
