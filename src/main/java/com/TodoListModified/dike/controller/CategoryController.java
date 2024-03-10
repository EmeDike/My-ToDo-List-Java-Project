package com.TodoListModified.dike.controller;

import com.TodoListModified.dike.data.models.Category;
import com.TodoListModified.dike.dtos.request.CategoryRequest;
import com.TodoListModified.dike.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

    @RestController
    @RequestMapping("/api/categories")
    public class CategoryController {

        @Autowired
        private CategoryService categoryService;

        @PostMapping
        public ResponseEntity<Category> createCategory(@RequestBody CategoryRequest categoryRequest) {
            Category createdCategory = categoryService.createCategory(categoryRequest);
            return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
        }

        @GetMapping
        public ResponseEntity<List<Category>> getAllCategories() {
            List<Category> categories = categoryService.getAllCategories();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Category> getCategoryById(@PathVariable("id") String id) {
            Optional<Category> category = categoryService.getCategoryById(id);
            return category.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        @PutMapping("/{id}")
        public ResponseEntity<Category> updateCategory(@PathVariable("id") String id,
                                                       @RequestBody CategoryRequest categoryRequest) {
            try {
                Category updatedCategory = categoryService.updateCategory(id, categoryRequest);
                return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteCategoryById(@PathVariable("id") String id) {
            try {
                categoryService.deleteCategoryById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }
