package com.TodoListModified.dike.services;

import com.TodoListModified.dike.data.models.Tag;
import com.TodoListModified.dike.data.models.Task;
import com.TodoListModified.dike.dtos.request.TagRequest;
import com.TodoListModified.dike.dtos.request.TaskRequest;

import java.util.List;

public interface TaskService {
    Task createTask(TaskRequest taskRequest);
    Task updateTask(String Id);
    List<Task> getAllTask();
    void deleteTask(String Id);

    Task getTaskById(String id);
}
