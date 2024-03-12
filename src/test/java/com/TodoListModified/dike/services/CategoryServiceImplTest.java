package com.TodoListModified.dike.services;

import com.TodoListModified.dike.data.models.Category;
import com.TodoListModified.dike.data.repositories.CategoryRepository;
import com.TodoListModified.dike.dtos.request.CategoryRequest;
import com.TodoListModified.dike.exception.InvalidCategoryNameException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @BeforeEach
    @AfterEach
    public void doThisAfterEachTest() {
        categoryRepository.deleteAll();
    }

    @Test
    public void testCreateCategory_ValidDetails_Success() {
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Urgent List");

        Category createdCategory = categoryService.createCategory(categoryRequest);

        assertNotNull(createdCategory);
        assertNotNull(createdCategory.getId());
        assertEquals(categoryRequest.getName(), createdCategory.getName());
    }

    @Test
    public void testCreateCategory_DuplicateName_ThrowsInvalidCategoryNameException() {
        CategoryRequest categoryRequest1 = new CategoryRequest();
        categoryRequest1.setName("Urgent List");
        categoryService.createCategory(categoryRequest1);

        CategoryRequest categoryRequest2 = new CategoryRequest();
        categoryRequest2.setName("Urgent List");

        assertThrows(InvalidCategoryNameException.class, () -> categoryService.createCategory(categoryRequest2));
    }

    @Test
    public void testUpdateCategory_ExistingId_Success() {
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Urgent List");
        Category createdCategory = categoryService.createCategory(categoryRequest);

        categoryRequest.setName("Updated Urgent List");
        Category updatedCategory = categoryService.updateCategory(createdCategory.getId(), categoryRequest);

        assertNotNull(updatedCategory);
        assertEquals(categoryRequest.getName(), updatedCategory.getName());
    }

    @Test
    public void testUpdateCategory_NonExistingId_ThrowsIllegalArgumentException() {
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Urgent List");

        assertThrows(IllegalArgumentException.class, () -> categoryService.updateCategory("nonexistentid", categoryRequest));
    }

    @Test
    public void testDeleteCategoryById_ExistingId_Success() {
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Urgent List");
        Category createdCategory = categoryService.createCategory(categoryRequest);

        assertDoesNotThrow(() -> categoryService.deleteCategoryById(createdCategory.getId()));
        assertFalse(categoryRepository.existsById(createdCategory.getId()));
    }

    @Test
    public void testDeleteCategoryById_NonExistingId_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> categoryService.deleteCategoryById("nonexistentid"));
    }

    @Test
    public void testGetCategoryById_ExistingId_ReturnsCategory() {
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Urgent List");
        Category createdCategory = categoryService.createCategory(categoryRequest);

        Optional<Category> retrievedCategory = categoryService.getCategoryById(createdCategory.getId());

        assertTrue(retrievedCategory.isPresent());
        assertEquals(createdCategory.getId(), retrievedCategory.get().getId());
    }

    @Test
    public void testGetCategoryById_NonExistingId_ReturnsEmptyOptional() {
        Optional<Category> retrievedCategory = categoryService.getCategoryById("nonexistentid");

        assertFalse(retrievedCategory.isPresent());
    }

    @Test
    public void testGetAllCategories() {
        CategoryRequest categoryRequest1 = new CategoryRequest();
        categoryRequest1.setName("Category 1");
        categoryService.createCategory(categoryRequest1);

        CategoryRequest categoryRequest2 = new CategoryRequest();
        categoryRequest2.setName("Category 2");
        categoryService.createCategory(categoryRequest2);

        List<Category> categories = categoryService.getAllCategories();

        assertNotNull(categories);
        assertEquals(2, categories.size());
    }
}
