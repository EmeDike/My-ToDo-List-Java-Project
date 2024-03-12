package com.TodoListModified.dike.dtos.response;

import com.TodoListModified.dike.data.models.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor

public class ApiResponse extends Category {
        private boolean isSuccessful;
        private Object data;
    }
