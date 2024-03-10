// TagServiceImplementation.java

package com.TodoListModified.dike.services;

import com.TodoListModified.dike.data.models.Tag;
import com.TodoListModified.dike.data.repositories.TagRepository;
import com.TodoListModified.dike.dtos.request.TagRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImplementation implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag createTag(TagRequest tagRequest) {
        if (tagRequest == null || tagRequest.getName() == null) {
            throw new IllegalArgumentException("TagRequest cannot be null and must contain a name");
        }

        Tag tag = new Tag();
        tag.setName(tagRequest.getName());
        return tagRepository.save(tag);
    }

    @Override
    public Tag updateTag(String id, TagRequest tagRequest) {
        Optional<Tag> optionalTag = tagRepository.findById(id);
        if (optionalTag.isPresent()) {
            Tag tag = optionalTag.get();
            tag.setName(tagRequest.getName());
            return tagRepository.save(tag);
        } else {
            throw new IllegalArgumentException("Tag with id " + id + " not found");
        }
    }

    @Override
    public List<Tag> getAllTag() {
        return tagRepository.findAll();
    }

    @Override
    public void deleteTag(String id) {
        tagRepository.deleteById(id);
    }
}
