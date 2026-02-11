package com.elias.inventorysystem.mapper;

import com.elias.inventorysystem.model.dto.request.ProductRequest;
import com.elias.inventorysystem.model.dto.response.ProductResponse;
import com.elias.inventorysystem.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    ProductResponse toProductResponse (Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "movements", ignore = true)
    @Mapping(target = "suppliers", ignore = true)
    Product toProduct(ProductRequest productRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "currentStock", ignore = true)
    @Mapping(target = "movements", ignore = true)
    @Mapping(target = "suppliers", ignore = true)
    void updateProduct(ProductRequest productRequest, @MappingTarget Product product);

}
