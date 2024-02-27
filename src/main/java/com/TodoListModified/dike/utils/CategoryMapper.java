package com.TodoListModified.dike.utils;

import com.TodoListModified.dike.data.models.Category;
import com.TodoListModified.dike.dtos.request.CategoryRequest;

public class CategoryMapper {
    private static Category mapCategoryRequestToCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        return category;
    }
}