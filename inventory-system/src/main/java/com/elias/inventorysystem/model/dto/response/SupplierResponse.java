package com.elias.inventorysystem.model.dto.response;

import java.util.Set;

public record SupplierResponse(Long id,
                               String companyName,
                               String contactEmail,
                               String phone,
                               Set<ProductSummaryResponse> products
) {

    public record ProductSummaryResponse(Long id,
                                          String name) {}

}

