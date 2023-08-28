package com.project.mesi.service;

import com.project.mesi.dto.CategoryDto;
import com.project.mesi.entity.Category;

public interface CategoryService {

    void save(CategoryDto categoryDto);

    void delete(Category category);

    Category findByName(String name);

    Category findByIdCategory(Long id);

}
