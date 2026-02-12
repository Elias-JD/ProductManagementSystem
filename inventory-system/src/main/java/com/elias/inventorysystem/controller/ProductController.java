package com.elias.inventorysystem.controller;

import com.elias.inventorysystem.model.dto.request.ProductRequest;
import com.elias.inventorysystem.model.dto.response.ProductResponse;
import com.elias.inventorysystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {

        return ResponseEntity.ok(productService.findAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {

        return ResponseEntity.ok(this.productService.findProductById(id));
    }

    @PostMapping()
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest) {

        return new ResponseEntity<>(this.productService.createProduct(productRequest), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProductById(@PathVariable Long id, @RequestBody ProductRequest product) {

        return ResponseEntity.ok(this.productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        this.productService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }

}
