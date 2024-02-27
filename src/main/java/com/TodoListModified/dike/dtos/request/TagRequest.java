package com.TodoListModified.dike.dtos.request;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data

public class TagRequest {
    private String id;

    private String name;
}
