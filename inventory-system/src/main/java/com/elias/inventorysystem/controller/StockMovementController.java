package com.elias.inventorysystem.controller;

import com.elias.inventorysystem.model.dto.request.StockMovementRequest;
import com.elias.inventorysystem.model.dto.response.StockMovementResponse;
import com.elias.inventorysystem.service.StockMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stock-movements")
public class StockMovementController {

    @Autowired
    private StockMovementService stockMovementService;

    @GetMapping
    public ResponseEntity<List<StockMovementResponse>> getStockMovements() {

        return ResponseEntity.ok(stockMovementService.findAllMovements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockMovementResponse> getStockMovementById(@PathVariable Long id) {

        return ResponseEntity.ok(this.stockMovementService.findMovementById(id));
    }

    @PostMapping()
    public ResponseEntity<StockMovementResponse> addStockMovement(@RequestBody StockMovementRequest stockMovementRequest) {

        return new ResponseEntity<>(this.stockMovementService.registerMovement(stockMovementRequest), HttpStatus.CREATED);
    }

}
