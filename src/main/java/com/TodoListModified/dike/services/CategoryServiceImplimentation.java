package com.TodoListModified.dike.services;

import com.TodoListModified.dike.data.models.Category;
import com.TodoListModified.dike.dtos.request.CategoryRequest;

import java.util.List;
import java.util.Optional;

public class CategoryServiceImplimentation implements CategoryService {
    @Override
    public Category createCategory(CategoryRequest categoryRequest) {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }

    @Override
    public Optional<Category> getCategoryById(String id) {
        return Optional.empty();
    }

    @Override
    public Category updateCategory(String id, CategoryRequest categoryRequest) {
        return null;
    }

    @Override
    public void deleteCategoryById(String id) {

    }
}
