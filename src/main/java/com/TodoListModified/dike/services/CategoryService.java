package com.TodoListModified.dike.services;

import com.TodoListModified.dike.data.models.Category;
import com.TodoListModified.dike.dtos.request.CategoryRequest;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category createCategory(CategoryRequest categoryRequest);

    List<Category> getAllCategories();

    Optional<Category> getCategoryById(String id);

    Category updateCategory(String id, CategoryRequest categoryRequest);

    void deleteCategoryById(String id);
}
