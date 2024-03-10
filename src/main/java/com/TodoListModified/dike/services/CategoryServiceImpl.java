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

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(CategoryRequest categoryRequest) {
        validateCategoryRequest(categoryRequest);

        Category category = new Category();
        category.setName(categoryRequest.getName());
        return categoryRepository.save(category);
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
        Category category = getCategoryByIdFromRepository(id);

        category.setName(categoryRequest.getName());
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(String id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Category with id " + id + " not found");
        }
    }

    private void validateCategoryRequest(CategoryRequest categoryRequest) {
        if (categoryRequest == null || categoryRequest.getName() == null || categoryRequest.getName().trim().isEmpty()) {
            throw new InvalidCategoryNameException("Category name cannot be null or empty");
        }

        if (categoryExist(categoryRequest.getName())) {
            throw new InvalidCategoryNameException("Category name already exists: " + categoryRequest.getName());
        }
    }

    private boolean categoryExist(String name) {
        return categoryRepository.findCategoryByName(name) != null;
    }

    private Category getCategoryByIdFromRepository(String id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElseThrow(() -> new IllegalArgumentException("Category with id " + id + " not found"));
    }
}
