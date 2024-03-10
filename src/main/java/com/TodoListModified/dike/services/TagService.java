package com.TodoListModified.dike.services;

import com.TodoListModified.dike.data.models.Tag;
import com.TodoListModified.dike.dtos.request.TagRequest;

import java.util.List;

public interface TagService {
    Tag createTag(TagRequest tagRequest);
    Tag updateTag(String id, TagRequest tagRequest);

    List<Tag> getAllTag();
    void deleteTag(String id);
}
