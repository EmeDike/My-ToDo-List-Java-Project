package com.TodoListModified.dike.services;

import com.TodoListModified.dike.data.models.Task;
import com.TodoListModified.dike.data.repositories.TaskRepository;
import com.TodoListModified.dike.dtos.request.TaskRequest;
import com.TodoListModified.dike.exception.InvalidTaskDetailsException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class TaskServiceImplimentationTest {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskService taskService;

    @BeforeEach
    @AfterEach
    public void doThisAfterEachTest(){
        taskRepository.deleteAll();
    }

    @Test
    public void testCreateTask_ValidDetails_Success() {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setTitle("Task Title");
        taskRequest.setDescription("Task Description");

        assertDoesNotThrow(() -> taskService.createTask(taskRequest));
        assertNotNull(taskRequest.getId());
    }

    @Test
    public void testCreateTask_NullTask_ThrowsInvalidTaskDetailsException() {
        assertThrows(InvalidTaskDetailsException.class, () -> taskService.createTask(null));
    }

    @Test
    public void testCreateTask_NullTitle_ThrowsInvalidTaskDetailsException() {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setDescription("Task Description");

        assertThrows(InvalidTaskDetailsException.class, () -> taskService.createTask(taskRequest));
    }

    @Test
    public void testCreateTask_EmptyTitle_ThrowsInvalidTaskDetailsException() {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setTitle("");
        taskRequest.setDescription("Task Description");

        assertThrows(InvalidTaskDetailsException.class, () -> taskService.createTask(taskRequest));
    }

    @Test
    public void testGetTaskById_ExistingId_ReturnsTask() {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setTitle("Task Title");
        taskRequest.setDescription("Task Description");
        taskRepository.save(taskRequest);

        Task retrievedTask = taskService.getTaskById(task.getId());

        assertNotNull(retrievedTask);
        assertEquals(task.getId(), retrievedTask.getId());
    }

    @Test
    public void testGetTaskById_NonExistingId_ThrowsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById(String.valueOf(-1L)));
    }

    @Test
    public void testGetAllTasks_NoTasks_ReturnsEmptyList() {
        List<Task> tasks = taskService.getAllTasks();
        assertTrue(tasks.isEmpty());
    }

}