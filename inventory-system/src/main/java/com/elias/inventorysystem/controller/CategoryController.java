package com.elias.inventorysystem.controller;

import com.elias.inventorysystem.model.dto.request.CategoryRequest;
import com.elias.inventorysystem.model.dto.response.CategoryResponse;
import com.elias.inventorysystem.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getCategories() {

        return ResponseEntity.ok(categoryService.findAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {

        return ResponseEntity.ok(this.categoryService.findCategoryById(id));
    }

    @PostMapping()
    public ResponseEntity<CategoryResponse> addCategory(@Valid @RequestBody CategoryRequest categoryRequest) {

        return new ResponseEntity<>(this.categoryService.createCategory(categoryRequest), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategoryById(@PathVariable Long id, @Valid @RequestBody CategoryRequest categoryRequest) {

        return ResponseEntity.ok(this.categoryService.updateCategory(id, categoryRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {

        this.categoryService.deleteCategory(id);

        return ResponseEntity.noContent().build();
    }
}
