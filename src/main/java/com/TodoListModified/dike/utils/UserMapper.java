package com.TodoListModified.dike.utils;

import com.TodoListModified.dike.data.models.User;
import com.TodoListModified.dike.dtos.request.UserRequest;

public class UserMapper {
    private static User mapUserRequestToUser(UserRequest userRequest){
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setUsername(user.getUsername());
        return user;
    }
}
