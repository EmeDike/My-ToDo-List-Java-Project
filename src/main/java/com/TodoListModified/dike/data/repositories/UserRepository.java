package com.TodoListModified.dike.data.repositories;

import com.TodoListModified.dike.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends MongoRepository <User, String> {
    User findByUsername(String username);

    User findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
