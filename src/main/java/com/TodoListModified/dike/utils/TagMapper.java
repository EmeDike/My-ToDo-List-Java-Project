package com.TodoListModified.dike.utils;

import com.TodoListModified.dike.data.models.Tag;
import com.TodoListModified.dike.dtos.request.TagRequest;

public class TagMapper {
    private static Tag mapTagRequestToTag(TagRequest tagRequest){
        Tag tag = new Tag();
        tag.setName(tagRequest.getName());
        return tag;
    }
}
