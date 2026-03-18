package com.aranna.backend.service;

import com.aranna.backend.repo.UserRepo;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aranna.backend.model.Category;
import com.aranna.backend.repo.CategoryRepo;

@Service
public class CategoryService {
  
    @Autowired
    private CategoryRepo categoryRepo;


    public @Nullable Object findAll() {
        return categoryRepo.findAll();
    }

    public @Nullable Object save(Category entity) {
        var category= categoryRepo.save(entity);
        return category;
    }

    public @Nullable Object updateCategory(String categoryName, Category entity) {
        return categoryRepo.findByCategoryName(categoryName);
    }

    public @Nullable Object deleteCategory(String categoryName) {
        var category=categoryRepo.findByCategoryName(categoryName);
        categoryRepo.deleteById(category.getCategoryId());
        var res=categoryRepo.findByCategoryName(categoryName);
        return res;
    }
}
