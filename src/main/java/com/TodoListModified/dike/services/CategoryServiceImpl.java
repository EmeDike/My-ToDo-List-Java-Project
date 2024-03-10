// CategoryServiceImpl.java

package com.TodoListModified.dike.services;

import com.TodoListModified.dike.data.models.Category;
import com.TodoListModified.dike.data.repositories.CategoryRepository;
import com.TodoListModified.dike.dtos.request.CategoryRequest;
import com.TodoListModified.dike.exception.InvalidCategoryNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(CategoryRequest categoryRequest) {
        if (categoryExist(categoryRequest.getName())) {
            throw new InvalidCategoryNameException("Category name already exists: " + categoryRequest.getName());
        }
        Category category = new Category();
        category.setName(categoryRequest.getName());
        return categoryRepository.save(category);
    }

    private boolean categoryExist(String name) {
        return categoryRepository.findCategoryByName(name) != null;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(String id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category updateCategory(String id, CategoryRequest categoryRequest) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setName(categoryRequest.getName());
            return categoryRepository.save(category);
        } else {
            throw new IllegalArgumentException("Category with id " + id + " not found");
        }
    }

    @Override
    public void deleteCategoryById(String id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Category with id " + id + " not found");
        }
    }
}
