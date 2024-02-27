package com.TodoListModified.dike.services;

import com.TodoListModified.dike.data.repositories.UserRepository;
import com.TodoListModified.dike.dtos.request.LoginRequest;
import com.TodoListModified.dike.dtos.request.UserRequest;
import com.TodoListModified.dike.exception.InvalidDetailsException;
import com.TodoListModified.dike.exception.UserExistException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceImplementationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    @AfterEach
    public void doThisAfterEachTest() {
        userRepository.deleteAll();
    }

    @Test
    public void testUserRegistration_WithValidDetails_Success() {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("dicksoneme64@gmail.com");
        userRequest.setPassword("dikedivine");
        userRequest.setUsername("dikeeme");

        assertDoesNotThrow(() -> userService.register(userRequest));
    }

    @Test
    public void testUserRegistration_WithExistingEmail_ThrowsUserExistException() {
        UserRequest userRequest1 = new UserRequest();
        userRequest1.setEmail("dicksoneme64@gmail.com");
        userRequest1.setPassword("dikedivine");
        userRequest1.setUsername("dikeeme");

        UserRequest userRequest2 = new UserRequest();
        userRequest2.setEmail("dicksoneme64@gmail.com");
        userRequest2.setPassword("password2");
        userRequest2.setUsername("username2");
        userRequest2.setPhoneNumber("08106040947");

        userService.register(userRequest1);

        assertThrows(UserExistException.class, () -> userService.register(userRequest2));
    }

    @Test
    public void testUserRegistration_WithExistingUsername_ThrowsUserExistException() {
        UserRequest userRequest1 = new UserRequest();
        userRequest1.setEmail("email1@gmail.com");
        userRequest1.setPassword("password1");
        userRequest1.setUsername("dikeeme");

        UserRequest userRequest2 = new UserRequest();
        userRequest2.setEmail("email2@gmail.com");
        userRequest2.setPassword("password2");
        userRequest2.setUsername("dikeeme");

        userService.register(userRequest1);

        assertThrows(UserExistException.class, () -> userService.register(userRequest2));
    }

    @Test
    public void testUserLogin_WithCorrectCredentials_Success() {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("dicksoneme64@gmail.com");
        userRequest.setPassword("dikedivine");
        userRequest.setUsername("dikeeme");

        userService.register(userRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("dicksoneme64@gmail.com");
        loginRequest.setPassword("dikedivine");

        assertDoesNotThrow(() -> userService.login(loginRequest));
    }

    @Test
    public void testUserLogin_WithIncorrectPassword_ThrowsInvalidDetailsException() {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("dicksoneme64@gmail.com");
        userRequest.setPassword("dikedivine");
        userRequest.setUsername("dikeeme");

        userService.register(userRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("dicksoneme64@gmail.com");
        loginRequest.setPassword("incorrect_password");

        assertThrows(InvalidDetailsException.class, () -> userService.login(loginRequest));
    }

    @Test
    public void testUserRegistration_WithInvalidEmailFormat_ThrowsInvalidDetailsException() {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("invalid_email_format");
        userRequest.setPassword("password123");
        userRequest.setUsername("username");

        assertThrows(InvalidDetailsException.class, () -> userService.register(userRequest));
    }

    @Test
    public void testUserRegistration_WithWeakPassword_ThrowsInvalidDetailsException() {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("validemail@example.com");
        userRequest.setPassword("weak");
        userRequest.setUsername("username");

        assertThrows(InvalidDetailsException.class, () -> userService.register(userRequest));
    }

    @Test
    public void testUserRegistration_WithEmptyUsername_ThrowsInvalidDetailsException() {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("validemail@example.com");
        userRequest.setPassword("strongPassword123");
        userRequest.setUsername("");

        assertThrows(InvalidDetailsException.class, () -> userService.register(userRequest));
    }
}
