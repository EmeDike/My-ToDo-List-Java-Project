package com.TodoListModified.dike.dtos.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class UserRequest {
    private String Id;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
}
