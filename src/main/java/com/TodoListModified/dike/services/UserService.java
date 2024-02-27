package com.TodoListModified.dike.services;

import com.TodoListModified.dike.data.models.User;
import com.TodoListModified.dike.dtos.request.LoginRequest;
import com.TodoListModified.dike.dtos.request.UserRequest;

public interface UserService {
    boolean login(LoginRequest userRequest);
    boolean logout(User user);
    void deleteUser(String id);

    void register(UserRequest userRequest);
}
