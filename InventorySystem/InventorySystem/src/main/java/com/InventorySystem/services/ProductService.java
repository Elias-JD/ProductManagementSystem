package com.InventorySystem.services;

import com.InventorySystem.exceptions.ResourceNotFoundException;
import com.InventorySystem.models.entities.Product;
import com.InventorySystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found with the id : " + id));
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {

        Product dbProduct = this.findProductById(id);

        dbProduct.setDescription(product.getDescription());
        dbProduct.setPrice(product.getPrice());
        dbProduct.setStock(product.getStock());
        dbProduct.setCategory(product.getCategory());

        return productRepository.save(dbProduct);

    }

    @Override
    public void deleteProduct(Long id) {

        Product product = this.findProductById(id);

        productRepository.delete(product);
    }
}
