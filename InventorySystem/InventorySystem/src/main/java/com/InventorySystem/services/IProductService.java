package com.InventorySystem.services;

import com.InventorySystem.models.entities.Product;

import java.util.List;

public interface IProductService {

    List<Product> findAllProducts();

    Product findProductById(Long id);

    Product createProduct(Product product);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);
}
