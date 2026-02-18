package com.elias.inventorysystem.model.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Set;

public record ProductRequest(
        @NotBlank(message = "SKU is required")
        String sku,

        @NotBlank(message = "Product name is required")
        String name,

        String description,

        @NotNull(message = "Stock is required")
        @Min(value = 0, message = "Stock cannot be negative")
        Integer currentStock,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.01", message = "Price must be greater than 0")
        BigDecimal unitPrice,

        @NotNull(message = "Category ID is required")
        Long categoryId,

        @NotEmpty(message = "At least one supplier ID is required")
        Set<Long> supplierIds
) {
}
