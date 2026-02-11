package com.elias.inventorysystem.model.dto.request;

import com.elias.inventorysystem.model.entity.MovementType;

public record StockMovementRequest(Long productId,
                                   MovementType type,
                                   Integer quantity,
                                   String reason
) {
}
