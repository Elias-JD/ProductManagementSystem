package com.elias.inventorysystem.model.dto.response;


import java.math.BigDecimal;
import java.util.Set;

public record ProductResponse(
        Long id,
        String sku,
        String name,
        String description,
        Integer currentStock,
        BigDecimal unitPrice,
        Long categoryId,
        String categoryName,
        Set<SupplierSummaryResponse> suppliers
) {
    public record SupplierSummaryResponse(Long id,
                                          String companyName) {}
}


