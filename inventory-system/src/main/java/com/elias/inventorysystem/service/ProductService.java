package com.elias.inventorysystem.service;

import com.elias.inventorysystem.exception.ResourceNotFoundException;
import com.elias.inventorysystem.mapper.ProductMapper;
import com.elias.inventorysystem.model.dto.request.ProductRequest;
import com.elias.inventorysystem.model.dto.response.ProductResponse;
import com.elias.inventorysystem.model.entity.Category;
import com.elias.inventorysystem.model.entity.Product;
import com.elias.inventorysystem.model.entity.Supplier;
import com.elias.inventorysystem.repository.CategoryRepository;
import com.elias.inventorysystem.repository.ProductRepository;
import com.elias.inventorysystem.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private  final SupplierRepository supplierRepository;


    @Transactional(readOnly = true)
    public List<ProductResponse> findAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .toList();
    }

    public ProductResponse findProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found with the id : " + id));

        return productMapper.toProductResponse(product);

    }

    @Transactional
    public ProductResponse createProduct(ProductRequest productRequest) {

        Category newCategory = categoryRepository.findById(productRequest.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + productRequest.categoryId()));

        Product product = productMapper.toProduct(productRequest);
        product.setCategory(newCategory);

        List<Supplier> suppliers = supplierRepository.findAllById(productRequest.supplierIds());

        supplierValidator(suppliers, productRequest);

        product.setSuppliers(new HashSet<>(suppliers));


        return productMapper.toProductResponse(productRepository.save(product));
    }

    @Transactional
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {

        Product dbProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found with the id : " + id));

        productMapper.updateProduct(productRequest, dbProduct);

        if (!dbProduct.getCategory().getId().equals(productRequest.categoryId())) {
            Category newCategory = categoryRepository.findById(productRequest.categoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + productRequest.categoryId()));
            dbProduct.setCategory(newCategory);
        }

        List<Supplier> suppliers = supplierRepository.findAllById(productRequest.supplierIds());
        supplierValidator(suppliers, productRequest);

        dbProduct.getSuppliers().clear();
        dbProduct.getSuppliers().addAll(suppliers);

        return productMapper.toProductResponse(productRepository.save(dbProduct));

    }

    public void deleteProduct(Long id) {

        Product dbProduct = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found with the id : " + id));
        productRepository.delete(dbProduct);

    }


    private void supplierValidator(List<Supplier> suppliers, ProductRequest productRequest) {

        if (suppliers.size() != productRequest.supplierIds().size()) {
            throw new ResourceNotFoundException("Some suppliers were not found");
        }
        else if (suppliers.isEmpty()) {
            throw new ResourceNotFoundException("The supplier(s) are required");
        }

    }

}
