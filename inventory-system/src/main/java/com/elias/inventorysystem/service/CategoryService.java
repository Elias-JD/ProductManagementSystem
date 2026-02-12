package com.elias.inventorysystem.service;

import com.elias.inventorysystem.exception.ResourceNotFoundException;
import com.elias.inventorysystem.mapper.CategoryMapper;
import com.elias.inventorysystem.model.dto.request.CategoryRequest;
import com.elias.inventorysystem.model.dto.response.CategoryResponse;
import com.elias.inventorysystem.model.entity.Category;
import com.elias.inventorysystem.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Transactional(readOnly = true)
    public List<CategoryResponse> findAllCategories() {

        return categoryRepository.findAllCategories();
    }

    public CategoryResponse findCategoryById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found with the id : " + id));

        return categoryMapper.toCategoryResponse(category);
    }

    public CategoryResponse createCategory(CategoryRequest categoryRequest) {

        Category category = categoryMapper.toCategory(categoryRequest);

        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    @Transactional
    public CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found with the id : " + id));

        categoryMapper.updateCategory(categoryRequest, category);


        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    public void deleteCategory(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found with the id : " + id));

        categoryRepository.delete(category);
    }
}
