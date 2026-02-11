package com.elias.inventorysystem.model.dto.request;

import java.math.BigDecimal;
import java.util.Set;

public record ProductRequest(
                             String sku,
                             String name,
                             String description,
                             Integer currentStock,
                             BigDecimal unitPrice,
                             Long categoryId,
                             Set<Long> supplierIds
) {
}
