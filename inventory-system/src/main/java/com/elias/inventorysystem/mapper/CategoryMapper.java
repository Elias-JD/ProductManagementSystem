package com.elias.inventorysystem.mapper;

import com.elias.inventorysystem.model.dto.request.CategoryRequest;
import com.elias.inventorysystem.model.dto.response.CategoryResponse;
import com.elias.inventorysystem.model.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "totalProducts", expression = "java(Long.valueOf(category.getProducts().size()))")
    CategoryResponse toCategoryResponse(Category category);

    @Mapping(target = "products", ignore = true)
    @Mapping(target = "id", ignore = true)
    Category toCategory(CategoryRequest categoryRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    void updateCategory(CategoryRequest categoryRequest, @MappingTarget Category category);
}