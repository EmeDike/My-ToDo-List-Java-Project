package com.TodoListModified.dike.services;

import com.TodoListModified.dike.data.models.User;
import com.TodoListModified.dike.dtos.request.LoginRequest;
import com.TodoListModified.dike.dtos.request.UserRequest;

import java.util.List;

public interface UserService {
    boolean login(LoginRequest userRequest);
    boolean logout(User user);
    void deleteUser(String id);

    User register(UserRequest userRequest);

    List<User> getAllUsers();

    User getUserById(String id);

    void deleteUserById(String id);
}
