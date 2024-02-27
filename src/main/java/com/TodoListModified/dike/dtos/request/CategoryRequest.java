package com.TodoListModified.dike.dtos.request;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document

public class CategoryRequest {
    private String Id;
    private String name;
}
