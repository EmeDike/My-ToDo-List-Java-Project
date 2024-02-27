package com.TodoListModified.dike.services;

import com.TodoListModified.dike.data.models.User;
import com.TodoListModified.dike.data.repositories.UserRepository;
import com.TodoListModified.dike.dtos.request.LoginRequest;
import com.TodoListModified.dike.dtos.request.UserRequest;
import com.TodoListModified.dike.exception.InvalidDetailsException;
import com.TodoListModified.dike.exception.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void register(UserRequest userRequest) {
        if (!isValidEmail(userRequest.getEmail())) {
            throw new InvalidDetailsException("Invalid email format: " + userRequest.getEmail());
        }

        if (!isStrongPassword(userRequest.getPassword())) {
            throw new InvalidDetailsException("Weak password: " + userRequest.getPassword());
        }

        if (userRequest.getUsername() == null || userRequest.getUsername().isEmpty()) {
            throw new InvalidDetailsException("Username cannot be empty");
        }

        if (userExistByUsername(userRequest.getUsername())) {
            throw new UserExistException(userRequest.getUsername() + " already exists");
        }

        if (userExistByEmail(userRequest.getEmail())) {
            throw new UserExistException(userRequest.getEmail() + " already exists");
        }

        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        userRepository.save(user);
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    private boolean isStrongPassword(String password) {
        return password.length() >= 8;
    }

    @Override
    public boolean login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());

        if (user == null) {
            throw new InvalidDetailsException("User with email " + loginRequest.getEmail() + " does not exist");
        }

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new InvalidDetailsException("Incorrect password for user with email " + loginRequest.getEmail());
        }

        return true;
    }

    @Override
    public boolean logout(User user) {
        return false;
    }

    @Override
    public void deleteUser(String id) {
    }

    private boolean userExistByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }

    private boolean userExistByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }
}
