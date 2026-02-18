package com.elias.inventorysystem.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequest(@NotBlank(message = "Category name is required")
                              String name,
                              @Size(max = 255, message = "Description cannot exceed 255 characters")
                              String description
) {
}
