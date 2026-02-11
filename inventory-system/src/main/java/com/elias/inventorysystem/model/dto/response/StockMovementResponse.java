package com.elias.inventorysystem.model.dto.response;

import com.elias.inventorysystem.model.entity.MovementType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record StockMovementResponse(Long id,
                                    MovementType type,
                                    Integer quantity,
                                    String reason,
                                    LocalDateTime createdAt,
                                    String productName,
                                    BigDecimal unitPriceProduct
) {
}
