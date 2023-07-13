package com.project.mesi.service.impl;

import com.project.mesi.dto.CategoryDto;
import com.project.mesi.entity.Category;
import com.project.mesi.repository.CategoryRepository;
import com.project.mesi.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void save(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
    }

    @Override
    public void delete(Category category) {

    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
