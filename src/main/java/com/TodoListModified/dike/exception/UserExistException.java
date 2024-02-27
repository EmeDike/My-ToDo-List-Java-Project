package com.TodoListModified.dike.exception;

import com.TodoListModified.dike.TodoListModifiedApplication;

public class UserExistException extends TodoListModifiedException {
    public UserExistException(String message) {
        super(message);
    }
}
