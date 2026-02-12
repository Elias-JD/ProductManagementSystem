package com.elias.inventorysystem.controller;

import com.elias.inventorysystem.model.dto.request.SupplierRequest;
import com.elias.inventorysystem.model.dto.response.SupplierResponse;
import com.elias.inventorysystem.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public ResponseEntity<List<SupplierResponse>> getSuppliers() {

        return ResponseEntity.ok(supplierService.findAllSuppliers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponse> getSupplierById(@PathVariable Long id) {

        return ResponseEntity.ok(supplierService.findSupplierById(id));
    }


    @PostMapping()
    public ResponseEntity<SupplierResponse> addSupplier(@RequestBody SupplierRequest supplierRequest) {

        return new ResponseEntity<>(supplierService.createSupplier(supplierRequest), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponse> addSupplier(@PathVariable Long id, @RequestBody SupplierRequest supplierRequest) {

        return ResponseEntity.ok(supplierService.updateSupplier(id, supplierRequest));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);

        return ResponseEntity.noContent().build();
    }
}
