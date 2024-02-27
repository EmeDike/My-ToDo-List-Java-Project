package com.TodoListModified.dike.data.models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Tag {
    private String id;
    private String name;
}
