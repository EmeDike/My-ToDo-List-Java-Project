package com.TodoListModified.dike.data.repositories;

import com.TodoListModified.dike.data.models.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TaskRepository extends MongoRepository <Task, String>{
    Task findTaskById(String Id);
}
