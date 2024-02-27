package com.TodoListModified.dike.utils;

import com.TodoListModified.dike.data.models.Task;
import com.TodoListModified.dike.dtos.request.TaskRequest;
import org.springframework.stereotype.Component;
@Component
public class TaskMapper {

    public static Task mapTaskRequestToTask(TaskRequest taskRequest) {

        Task task = new Task();
              task.setTitle(taskRequest.getTitle());
              task.setDescription(taskRequest.getDescription());
              task.setDueDate(taskRequest.getDueDate());
              task.setCompleted(taskRequest.isCompleted());
        return task;
    }
}
