package com.elias.inventorysystem.model.dto.response;


public record CategoryResponse(Long id,
                               String name,
                               String description,
                               Long totalProducts
) {
}
