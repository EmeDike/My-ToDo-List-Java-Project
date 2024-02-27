package com.TodoListModified.dike.data.models;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document

public class User {
    @Id
    private String Id;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
}
