// TagServiceImplementationTest.java

package com.TodoListModified.dike.services;

import com.TodoListModified.dike.data.models.Tag;
import com.TodoListModified.dike.data.repositories.TagRepository;
import com.TodoListModified.dike.dtos.request.TagRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.mongodb.assertions.Assertions.assertFalse;
import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class TagServiceImplementationTest {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TagService tagService;

    @BeforeEach
    @AfterEach
    public void doThisAfterEachTest() {
        tagRepository.deleteAll();
    }

    @Test
    public void testCreateTag() {
        TagRequest tagRequest = new TagRequest();
        tagRequest.setName("Work");

        Tag createdTag = tagService.createTag(tagRequest);

        assertNotNull(createdTag);
        assertNotNull(createdTag.getId());
        assertEquals(tagRequest.getName(), createdTag.getName());
    }

    @Test
    public void testUpdateTag() {
        TagRequest tagRequest = new TagRequest();
        tagRequest.setName("Home");
        Tag createdTag = tagService.createTag(tagRequest);

        tagRequest.setName("Updated Home");
        Tag updatedTag = tagService.updateTag(createdTag.getId(), tagRequest);

        assertNotNull(updatedTag);
        assertEquals(tagRequest.getName(), updatedTag.getName());
    }

    @Test
    public void testUpdateTag_NonExistentId() {
        TagRequest tagRequest = new TagRequest();
        tagRequest.setName("Personal");

        assertThrows(IllegalArgumentException.class, () -> {
            tagService.updateTag("nonexistentid", tagRequest);
        });
    }

    @Test
    public void testGetAllTag() {
        TagRequest tagRequest1 = new TagRequest();
        tagRequest1.setName("Personal");
        Tag tag1 = tagService.createTag(tagRequest1);

        TagRequest tagRequest2 = new TagRequest();
        tagRequest2.setName("Work");
        Tag tag2 = tagService.createTag(tagRequest2);

        List<Tag> tags = tagService.getAllTag();

        assertNotNull(tags);
        assertEquals(2, tags.size());
        assertTrue(tags.contains(tag1));
        assertTrue(tags.contains(tag2));
    }

    @Test
    public void testDeleteTag() {
        TagRequest tagRequest = new TagRequest();
        tagRequest.setName("Work");
        Tag createdTag = tagService.createTag(tagRequest);

        tagService.deleteTag(createdTag.getId());

        assertFalse(tagRepository.existsById(createdTag.getId()));
    }
}
