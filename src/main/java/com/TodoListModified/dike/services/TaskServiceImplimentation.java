package com.TodoListModified.dike.services;

import com.TodoListModified.dike.data.models.Task;
import com.TodoListModified.dike.dtos.request.TaskRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class TaskServiceImplimentation implements TaskService{
    @Override
    public Task createTask(TaskRequest taskRequest) {
        return null;
    }

    @Override
    public Task updateTask(String Id) {
        return null;
    }

    @Override
    public List<Task> getAllTask() {
        return null;
    }

    @Override
    public void deleteTask(String Id) {

    }
}
