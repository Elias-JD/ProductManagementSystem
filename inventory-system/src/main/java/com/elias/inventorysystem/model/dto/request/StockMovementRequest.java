package com.elias.inventorysystem.model.dto.request;

import com.elias.inventorysystem.model.entity.MovementType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record StockMovementRequest(@NotNull(message = "Product ID is required")
                                   Long productId,

                                   @NotNull(message = "Movement type is required")
                                   MovementType type,

                                   @NotNull(message = "Quantity is required")
                                   @Positive(message = "Quantity must be greater than zero")
                                   Integer quantity,

                                   @Size(max = 255)
                                   String reason
) {
}
