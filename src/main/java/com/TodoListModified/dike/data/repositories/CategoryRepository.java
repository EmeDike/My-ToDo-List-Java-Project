package com.TodoListModified.dike.data.repositories;

import com.TodoListModified.dike.data.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CategoryRepository extends MongoRepository<Category, String> {
    Category findCategoryById(String Id);
}
