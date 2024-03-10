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
public class TaskServiceImplementation implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImplementation(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(TaskRequest taskRequest) {
        validateTaskRequest(taskRequest);

        Task task = createTaskFromRequest(taskRequest);
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(String id, TaskRequest taskRequest) {
        Task task = getTaskByIdFromRepository(id);

        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setDueDate(taskRequest.getDueDate());
        task.setCompleted(taskRequest.isCompleted());

        return taskRepository.save(task);
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
        return getTaskByIdFromRepository(id);
    }

    private Task getTaskByIdFromRepository(String id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found"));
    }

    private void validateTaskRequest(TaskRequest taskRequest) {
        if (!isValidTitle(taskRequest.getTitle())) {
            throw new InvalidDetailsException("Invalid title: " + taskRequest.getTitle());
        }
    }

    private boolean isValidTitle(String title) {
        return title != null && !title.trim().isEmpty();
    }

    private Task createTaskFromRequest(TaskRequest taskRequest) {
        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setDueDate(taskRequest.getDueDate());
        task.setCompleted(taskRequest.isCompleted());
        return task;
    }
}
