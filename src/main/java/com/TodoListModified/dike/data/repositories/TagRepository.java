package com.TodoListModified.dike.data.repositories;

import com.TodoListModified.dike.data.models.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TagRepository extends MongoRepository <Tag, String> {
    Tag findTagById (String Id);
}
