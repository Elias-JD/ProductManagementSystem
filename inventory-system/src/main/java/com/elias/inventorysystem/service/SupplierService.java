package com.elias.inventorysystem.service;

import com.elias.inventorysystem.exception.ResourceInUseException;
import com.elias.inventorysystem.exception.ResourceNotFoundException;
import com.elias.inventorysystem.mapper.SupplierMapper;
import com.elias.inventorysystem.model.dto.request.SupplierRequest;
import com.elias.inventorysystem.model.dto.response.SupplierResponse;
import com.elias.inventorysystem.model.entity.Supplier;
import com.elias.inventorysystem.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    public SupplierService(SupplierRepository supplierRepository, SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
    }

    @Transactional(readOnly = true)
    public List<SupplierResponse> findAllSuppliers() {

        return supplierRepository.findAll()
                .stream()
                .map(supplierMapper::toSupplierResponse)
                .toList();
    }

    public SupplierResponse findSupplierById(Long id) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found with the id : + id"));

        return supplierMapper.toSupplierResponse(supplier);
    }

    @Transactional
    public SupplierResponse createSupplier(SupplierRequest supplierRequest) {

        Supplier supplier = supplierMapper.toSupplier(supplierRequest);

        return supplierMapper.toSupplierResponse(supplierRepository.save(supplier));
    }

    @Transactional
    public SupplierResponse updateSupplier(Long id , SupplierRequest supplierRequest) {

        Supplier supplierDb = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found with the id :" + id));

        supplierMapper.updateSupplier(supplierRequest, supplierDb);

        return supplierMapper.toSupplierResponse(supplierRepository.save(supplierDb));
    }

    public void deleteSupplier(Long id) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found with the id : + id"));

        if(!supplier.getProducts().isEmpty()){
            throw new ResourceInUseException("You cannot remove suppliers with associated products.");
        }

        supplierRepository.delete(supplier);

    }

}
