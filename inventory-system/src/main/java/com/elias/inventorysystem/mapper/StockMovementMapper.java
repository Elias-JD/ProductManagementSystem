package com.elias.inventorysystem.mapper;

import com.elias.inventorysystem.model.dto.request.StockMovementRequest;
import com.elias.inventorysystem.model.dto.response.StockMovementResponse;
import com.elias.inventorysystem.model.entity.StockMovement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StockMovementMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    StockMovement toStockMovement(StockMovementRequest stockMovementRequest);

    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "product.unitPrice", target = "unitPriceProduct")
    StockMovementResponse toStockMovementResponse(StockMovement stockMovement);
}
