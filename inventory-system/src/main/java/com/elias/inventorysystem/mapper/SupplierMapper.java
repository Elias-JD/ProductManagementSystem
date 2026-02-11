package com.elias.inventorysystem.mapper;

import com.elias.inventorysystem.model.dto.request.SupplierRequest;
import com.elias.inventorysystem.model.dto.response.SupplierResponse;
import com.elias.inventorysystem.model.entity.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SupplierMapper {


    SupplierResponse toSupplierResponse(Supplier supplier);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    Supplier toSupplier(SupplierRequest supplierRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    void updateSupplier(SupplierRequest supplierRequest, @MappingTarget Supplier supplier);


}
