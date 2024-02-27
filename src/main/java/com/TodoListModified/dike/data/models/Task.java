package com.TodoListModified.dike.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@Document

public class Task {
    private String id;

    private String title;
    private String description;
    private Date dueDate;
    private boolean completed;
}

