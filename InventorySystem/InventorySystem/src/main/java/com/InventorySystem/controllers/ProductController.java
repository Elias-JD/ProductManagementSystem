package com.InventorySystem.controllers;

import com.InventorySystem.models.entities.Product;
import com.InventorySystem.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(value = "http://localhost:4200")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.findAllProducts();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {

        Product product = this.productService.findProductById(id);

        return new ResponseEntity<>(product, HttpStatus.OK);

    }

    @PostMapping()
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {

        return new ResponseEntity<>(this.productService.createProduct(product), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable Long id, @RequestBody Product product) {

        Product dbProduct = this.productService.updateProduct(id, product);

        return new ResponseEntity<>(dbProduct, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        this.productService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }

}
