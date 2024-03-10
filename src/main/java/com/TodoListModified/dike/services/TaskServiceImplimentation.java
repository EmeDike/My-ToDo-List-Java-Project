// TaskServiceImplimentation.java

package com.TodoListModified.dike.services;

import com.TodoListModified.dike.data.models.Task;
import com.TodoListModified.dike.data.repositories.TaskRepository;
import com.TodoListModified.dike.dtos.request.TaskRequest;
import com.TodoListModified.dike.exception.InvalidDetailsException;
import com.TodoListModified.dike.exception.TaskNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImplimentation implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public Task createTask(TaskRequest taskRequest) {
        // Validate task title
        if (!isValidTitle(taskRequest.getTitle())) {
            throw new InvalidDetailsException("Invalid title: " + taskRequest.getTitle());
        }

        // Create a new Task entity
        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setDueDate(taskRequest.getDueDate());
        task.setCompleted(taskRequest.isCompleted());

        // Save the task to the database
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(String id, TaskRequest taskRequest) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setTitle(taskRequest.getTitle());
            task.setDescription(taskRequest.getDescription());
            task.setDueDate(taskRequest.getDueDate());
            task.setCompleted(taskRequest.isCompleted());
            return taskRepository.save(task);
        } else {
            throw new TaskNotFoundException("Task with id " + id + " not found");
        }
    }

    @Override
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    @Override
    public void deleteTask(String id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        } else {
            throw new TaskNotFoundException("Task with id " + id + " not found");
        }
    }

    @Override
    public Task getTaskById(String id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            return optionalTask.get();
        } else {
            throw new TaskNotFoundException("Task with id " + id + " not found");
        }
    }

    // Helper method to validate task title
    private boolean isValidTitle(String title) {
        return title != null && !title.trim().isEmpty();
    }
}
