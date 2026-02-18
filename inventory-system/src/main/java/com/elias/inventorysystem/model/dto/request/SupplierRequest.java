package com.elias.inventorysystem.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SupplierRequest(@NotBlank(message = "Company name is required")
                              String companyName,

                              @Email(message = "Invalid email format")
                              String contactEmail,

                              @NotBlank(message = "Phone number is required")
                              String phone
) {
}
