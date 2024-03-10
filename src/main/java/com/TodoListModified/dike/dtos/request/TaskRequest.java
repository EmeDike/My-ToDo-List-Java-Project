// TaskRequest.java

package com.TodoListModified.dike.dtos.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class TaskRequest {
    private Long id;
    private String title;
    private String description;
    private Date dueDate;
    private boolean completed;
}
