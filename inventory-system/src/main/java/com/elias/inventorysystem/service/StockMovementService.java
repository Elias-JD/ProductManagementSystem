package com.elias.inventorysystem.service;

import com.elias.inventorysystem.exception.ResourceNotFoundException;
import com.elias.inventorysystem.mapper.StockMovementMapper;
import com.elias.inventorysystem.model.dto.request.StockMovementRequest;
import com.elias.inventorysystem.model.dto.response.StockMovementResponse;
import com.elias.inventorysystem.model.entity.Product;
import com.elias.inventorysystem.model.entity.StockMovement;
import com.elias.inventorysystem.repository.ProductRepository;
import com.elias.inventorysystem.repository.StockMovementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;
    private final StockMovementMapper stockMovementMapper;
    private final ProductRepository productRepository;

    public StockMovementService(StockMovementRepository stockMovementRepository, StockMovementMapper stockMovementMapper, ProductRepository productRepository) {
        this.stockMovementRepository = stockMovementRepository;
        this.stockMovementMapper = stockMovementMapper;
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<StockMovementResponse> findAllMovements(){

        return stockMovementRepository.findAll()
                .stream()
                .map(stockMovementMapper::toStockMovementResponse)
                .toList();
    }


    public StockMovementResponse findMovementById(Long id) {

        StockMovement stockMovementDb = stockMovementRepository.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException("Resource not found with id " + id));

        return stockMovementMapper.toStockMovementResponse(stockMovementDb);
    }


    @Transactional
    public StockMovementResponse registerMovement(StockMovementRequest request) {

        Product product = productRepository.findById(request.productId())
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found with the id : " + request.productId()));

        product.updateStock(request.type(), request.quantity());

        StockMovement stockMovement = stockMovementMapper.toStockMovement(request);
        stockMovement.setProduct(product);

        return stockMovementMapper.
                toStockMovementResponse(stockMovementRepository.save(stockMovement));
    }


}
