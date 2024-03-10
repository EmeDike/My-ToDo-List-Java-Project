package com.TodoListModified.dike.services;

import com.TodoListModified.dike.data.models.User;
import com.TodoListModified.dike.data.repositories.UserRepository;
import com.TodoListModified.dike.dtos.request.LoginRequest;
import com.TodoListModified.dike.dtos.request.UserRequest;
import com.TodoListModified.dike.exception.InvalidDetailsException;
import com.TodoListModified.dike.exception.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(UserRequest userRequest) {
        validateUserRequest(userRequest);

        User user = createUserFromRequest(userRequest);
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());

        if (user == null || !user.getPassword().equals(loginRequest.getPassword())) {
            throw new InvalidDetailsException("Invalid email or password.");
        }

        return true;
    }

    @Override
    public boolean logout(User user) {
        // Implement logout logic here if needed
        return true;
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    private void validateUserRequest(UserRequest userRequest) {
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
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    private boolean isStrongPassword(String password) {
        return password.length() >= 8;
    }

    private boolean userExistByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    private boolean userExistByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    private User createUserFromRequest(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        return user;
    }
}
