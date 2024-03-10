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

    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImplementation(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag createTag(TagRequest tagRequest) {
        validateTagRequest(tagRequest);

        Tag tag = createTagFromRequest(tagRequest);
        return tagRepository.save(tag);
    }

    @Override
    public Tag updateTag(String id, TagRequest tagRequest) {
        Tag tag = getTagByIdFromRepository(id);

        tag.setName(tagRequest.getName());
        return tagRepository.save(tag);
    }

    @Override
    public List<Tag> getAllTag() {
        return tagRepository.findAll();
    }

    @Override
    public void deleteTag(String id) {
        tagRepository.deleteById(id);
    }

    @Override
    public Optional<Tag> getTagById(String id) {
        return tagRepository.findById(id);
    }

    private void validateTagRequest(TagRequest tagRequest) {
        if (tagRequest == null || tagRequest.getName() == null || tagRequest.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("TagRequest cannot be null and must contain a non-empty name");
        }
    }

    private Tag getTagByIdFromRepository(String id) {
        Optional<Tag> optionalTag = tagRepository.findById(id);
        return optionalTag.orElseThrow(() -> new IllegalArgumentException("Tag with id " + id + " not found"));
    }

    private Tag createTagFromRequest(TagRequest tagRequest) {
        Tag tag = new Tag();
        tag.setName(tagRequest.getName());
        return tag;
    }
}
