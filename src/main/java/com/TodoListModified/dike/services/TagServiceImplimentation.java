package com.TodoListModified.dike.services;

import com.TodoListModified.dike.data.models.Tag;
import com.TodoListModified.dike.dtos.request.TagRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class TagServiceImplimentation implements TagService {
    @Override
    public Tag createTag(TagRequest tagRequest) {
        return null;
    }

    @Override
    public Tag updateTag(String Id) {
        return null;
    }

    @Override
    public List<Tag> getAllTag() {
        return null;
    }

    @Override
    public void deleteTag(String Id) {

    }
}
