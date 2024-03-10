// TaskServiceImplimentationTest.java

package com.TodoListModified.dike.services;

import com.TodoListModified.dike.data.models.Task;
import com.TodoListModified.dike.data.repositories.TaskRepository;
import com.TodoListModified.dike.dtos.request.TaskRequest;
import com.TodoListModified.dike.exception.InvalidDetailsException;
import com.TodoListModified.dike.exception.TaskNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskServiceImplimentationTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    @BeforeEach
    @AfterEach
    public void doThisAfterEachTest() {
        taskRepository.deleteAll();
    }

    @Test
    public void testCreateTask_ValidDetails_Success() {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setTitle("Task Title");
        taskRequest.setDescription("Task Description");
        taskRequest.setDueDate(new Date());
        taskRequest.setCompleted(false);

        Task createdTask = taskService.createTask(taskRequest);

        assertNotNull(createdTask);
        assertNotNull(createdTask.getId());
    }
    @Test
    public void testCreateTask_NullTitle_ThrowsInvalidDetailsException() {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setDescription("Task Description");
        taskRequest.setDueDate(new Date());
        taskRequest.setCompleted(false);

        assertThrows(InvalidDetailsException.class, () -> taskService.createTask(taskRequest));
    }

    @Test
    public void testCreateTask_EmptyTitle_ThrowsInvalidDetailsException() {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setTitle("");
        taskRequest.setDescription("Task Description");
        taskRequest.setDueDate(new Date());
        taskRequest.setCompleted(false);

        assertThrows(InvalidDetailsException.class, () -> taskService.createTask(taskRequest));
    }

    @Test
    public void testUpdateTask_ExistingId_Success() {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setTitle("Task Title");
        taskRequest.setDescription("Task Description");
        taskRequest.setDueDate(new Date());
        taskRequest.setCompleted(false);

        Task createdTask = taskService.createTask(taskRequest);

        taskRequest.setTitle("Updated Task Title");
        Task updatedTask = taskService.updateTask(createdTask.getId(), taskRequest);

        assertNotNull(updatedTask);
        assertEquals(taskRequest.getTitle(), updatedTask.getTitle());
    }

    @Test
    public void testUpdateTask_NonExistingId_ThrowsTaskNotFoundException() {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setTitle("Task Title");
        taskRequest.setDescription("Task Description");
        taskRequest.setDueDate(new Date());
        taskRequest.setCompleted(false);

        assertThrows(TaskNotFoundException.class, () -> taskService.updateTask("nonexistentid", taskRequest));
    }

    @Test
    public void testGetAllTask() {
        TaskRequest taskRequest1 = new TaskRequest();
        taskRequest1.setTitle("Task Title 1");
        taskRequest1.setDescription("Task Description 1");
        taskRequest1.setDueDate(new Date());
        taskRequest1.setCompleted(false);
        taskService.createTask(taskRequest1);

        TaskRequest taskRequest2 = new TaskRequest();
        taskRequest2.setTitle("Task Title 2");
        taskRequest2.setDescription("Task Description 2");
        taskRequest2.setDueDate(new Date());
        taskRequest2.setCompleted(false);
        taskService.createTask(taskRequest2);

        List<Task> tasks = taskService.getAllTask();

        assertNotNull(tasks);
        assertEquals(2, tasks.size());
    }

    @Test
    public void testDeleteTask_ExistingId_Success() {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setTitle("Task Title");
        taskRequest.setDescription("Task Description");
        taskRequest.setDueDate(new Date());
        taskRequest.setCompleted(false);

        Task createdTask = taskService.createTask(taskRequest);

        assertDoesNotThrow(() -> taskService.deleteTask(createdTask.getId()));
        assertFalse(taskRepository.existsById(createdTask.getId()));
    }

    @Test
    public void testDeleteTask_NonExistingId_ThrowsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> taskService.deleteTask("nonexistentid"));
    }

    @Test
    public void testGetTaskById_ExistingId_ReturnsTask() {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setTitle("Task Title");
        taskRequest.setDescription("Task Description");
        taskRequest.setDueDate(new Date());
        taskRequest.setCompleted(false);

        Task createdTask = taskService.createTask(taskRequest);

        Task retrievedTask = taskService.getTaskById(createdTask.getId());

        assertNotNull(retrievedTask);
        assertEquals(createdTask.getId(), retrievedTask.getId());
    }

    @Test
    public void testGetTaskById_NonExistingId_ThrowsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById("nonexistentid"));
    }
}
